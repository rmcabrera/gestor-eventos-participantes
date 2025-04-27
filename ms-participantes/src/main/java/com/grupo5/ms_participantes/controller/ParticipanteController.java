package com.grupo5.ms_participantes.controller;

import com.grupo5.ms_participantes.entity.Participante;
import com.grupo5.ms_participantes.service.ParticipanteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/participantes")
public class ParticipanteController {

    private final ParticipanteService participanteService;

    public ParticipanteController(ParticipanteService participanteService) {
        this.participanteService = participanteService;
    }

    @Operation(summary = "Listar todos los participantes", description = "Devuelve una lista de todos los participantes registrados")
    @ApiResponse(responseCode = "200", description = "Lista de participantes")
    @GetMapping
    public List<Participante> listarTodos() {
        return participanteService.listarTodos();
    }

    @Operation(summary = "Obtener un participante por ID", description = "Devuelve los detalles de un participante basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participante encontrado"),
            @ApiResponse(responseCode = "404", description = "Participante no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Participante> obtenerParticipante(@PathVariable Long id) {
        Optional<Participante> participante = participanteService.buscarPorId(id);
        return participante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Guardar un nuevo participante", description = "Crea un nuevo participante en el sistema")
    @ApiResponse(responseCode = "201", description = "Participante creado")
    @PostMapping
    public ResponseEntity<Participante> guardarParticipante(@RequestBody Participante participante) {
        return new ResponseEntity<>(participanteService.guardar(participante), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar un participante", description = "Actualiza la información de un participante basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participante actualizado"),
            @ApiResponse(responseCode = "404", description = "Participante no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Participante> actualizarParticipante(@PathVariable Long id, @RequestBody Participante participante) {
        return new ResponseEntity<>(participanteService.actualizar(id, participante), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar un participante", description = "Elimina un participante del sistema basado en su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Participante eliminado"),
            @ApiResponse(responseCode = "404", description = "Participante no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarParticipante(@PathVariable Long id) {
        participanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar participante por email", description = "Devuelve los detalles de un participante basado en su correo electrónico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Participante encontrado"),
            @ApiResponse(responseCode = "404", description = "Participante no encontrado")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<Participante> buscarPorEmail(@PathVariable String email) {
        Optional<Participante> participante = participanteService.buscarPorEmail(email);
        return participante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
