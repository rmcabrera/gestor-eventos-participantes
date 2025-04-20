package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.entity.Participante;

import java.util.List;
import java.util.Optional;

public interface ParticipanteService {
    Participante guardar(Participante participante);
    Optional<Participante> buscarPorEmail(String email);
    Participante actualizar(Long id, Participante participante);
    void eliminar(Long id);
    Optional<Participante> buscarPorId(Long id);
    List<Participante> listarTodos();
}
