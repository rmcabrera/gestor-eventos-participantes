package com.grupo5.ms_participantes.controller;

import com.grupo5.ms_participantes.entity.Participante;
import com.grupo5.ms_participantes.service.ParticipanteService;
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

    @GetMapping
    public List<Participante> listarTodos() {
        return participanteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> obtenerParticipante(@PathVariable Long id) {
        Optional<Participante> participante = participanteService.buscarPorId(id);
        return participante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Participante> guardarParticipante(@RequestBody Participante participante) {
        return new ResponseEntity<>(participanteService.guardar(participante), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> actualizarParticipante(@PathVariable Long id, @RequestBody Participante participante) {
        return new ResponseEntity<>(participanteService.actualizar(id, participante), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarParticipante(@PathVariable Long id) {
        participanteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Participante> buscarPorEmail(@PathVariable String email) {
        Optional<Participante> participante = participanteService.buscarPorEmail(email);
        return participante.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
