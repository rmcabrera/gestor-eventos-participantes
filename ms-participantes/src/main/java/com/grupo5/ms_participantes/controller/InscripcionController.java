package com.grupo5.ms_participantes.controller;

import com.grupo5.ms_participantes.dto.EventoDTO;
import com.grupo5.ms_participantes.dto.InscripcionDTO;
import com.grupo5.ms_participantes.dto.InscripcionRequest;
import com.grupo5.ms_participantes.entity.Inscripcion;
import com.grupo5.ms_participantes.entity.Participante;
import com.grupo5.ms_participantes.service.EventoRestClient;
import com.grupo5.ms_participantes.service.InscripcionService;
import com.grupo5.ms_participantes.service.ParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/inscripciones")
public class InscripcionController {

    private final InscripcionService inscripcionService;
    private final ParticipanteService participanteService;
    private final EventoRestClient eventoRestClient;

    public InscripcionController(InscripcionService inscripcionService,
                                 ParticipanteService participanteService,
                                 EventoRestClient eventoRestClient) {
        this.inscripcionService = inscripcionService;
        this.participanteService = participanteService;
        this.eventoRestClient = eventoRestClient;
    }

    @PostMapping
    public ResponseEntity<?> registrarInscripcion(@RequestBody InscripcionRequest inscripcionRequest) {
        if (inscripcionRequest.getIdEvento() == null) {
            return ResponseEntity.badRequest().body("El ID del evento no puede ser nulo");
        }

        EventoDTO evento = eventoRestClient.obtenerEventoPorId(inscripcionRequest.getIdEvento());
        if (evento == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El evento no existe");
        }

        Participante participante = participanteService.buscarPorEmail(inscripcionRequest.getEmail())
                .orElseGet(() -> {
                    if (inscripcionRequest.getNombres() == null || inscripcionRequest.getNombres().isEmpty() ||
                            inscripcionRequest.getApellidos() == null || inscripcionRequest.getApellidos().isEmpty() ||
                            inscripcionRequest.getTelefono() == null || inscripcionRequest.getTelefono().isEmpty()) {

                        return null;
                    }

                    Participante nuevo = new Participante();
                    nuevo.setEmail(inscripcionRequest.getEmail());
                    nuevo.setNombres(inscripcionRequest.getNombres());
                    nuevo.setApellidos(inscripcionRequest.getApellidos());
                    nuevo.setTelefono(inscripcionRequest.getTelefono());
                    nuevo.setRegistradoEn(LocalDateTime.now());
                    return participanteService.guardar(nuevo);
                });

        if (participante == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El participante no está registrado y faltan datos necesarios para su registro");
        }


        Optional<Inscripcion> inscripcionExistente = inscripcionService.buscarPorParticipanteYEvento(participante.getId(), inscripcionRequest.getIdEvento());

        if (inscripcionExistente.isPresent()) {
            Inscripcion inscripcion = inscripcionExistente.get();

            if ("cancelado".equals(inscripcion.getEstado())) {
                inscripcion.setEstado("registrado");
                inscripcionService.guardar(inscripcion);
                return ResponseEntity.ok("Inscripción reactivada");
            }

            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya estás inscrito en este evento");
        }


        long inscritos = inscripcionService.contarInscritosEnEvento(inscripcionRequest.getIdEvento());
        if (inscritos >= evento.getCupoMaximo()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El evento ya alcanzó su capacidad máxima");
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setParticipante(participante);
        inscripcion.setIdEvento(inscripcionRequest.getIdEvento());
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstado("registrado");
        Inscripcion guardada = inscripcionService.guardar(inscripcion);

        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    @GetMapping("/participante/{id}")
    public ResponseEntity<List<InscripcionDTO>> listarPorParticipante(@PathVariable Long id) {
        List<Inscripcion> inscripciones = inscripcionService.listarPorParticipante(id);

        List<InscripcionDTO> inscripcionesDTO = inscripciones.stream().map(inscripcion -> {
            InscripcionDTO dto = new InscripcionDTO();
            dto.setId(inscripcion.getId());
            dto.setIdEvento(inscripcion.getIdEvento());
            dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
            dto.setEstado(inscripcion.getEstado());

            Participante participante = inscripcion.getParticipante();

            if (participante != null) {
                dto.setEmail(participante.getEmail());
                dto.setNombre(participante.getNombres());
            }

            EventoDTO evento = eventoRestClient.obtenerEventoPorId(inscripcion.getIdEvento());
            if (evento != null) {
                dto.setNombreEvento(evento.getNombre());
                dto.setLugarEvento(evento.getLugar());
            }

            return dto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(inscripcionesDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscripcion> obtenerInscripcion(@PathVariable Long id) {
        return inscripcionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelarInscripcion(@PathVariable Long id) {

        Optional<Inscripcion> inscripcionExistente = inscripcionService.buscarPorId(id);
        if (!inscripcionExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Inscripción no encontrada");
        }

        Inscripcion inscripcion = inscripcionExistente.get();

        if ("cancelado".equals(inscripcion.getEstado())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La inscripción ya está cancelada");
        }

        inscripcion.setEstado("cancelado");

        inscripcionService.guardar(inscripcion);

        return ResponseEntity.ok("Inscripción cancelada");
    }

}