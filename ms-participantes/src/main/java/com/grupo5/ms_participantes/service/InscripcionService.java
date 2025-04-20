package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.entity.Inscripcion;

import java.util.List;
import java.util.Optional;

public interface InscripcionService {
    Inscripcion guardar(Inscripcion inscripcion);
    List<Inscripcion> listarPorEvento(Long idEvento);
    List<Inscripcion> listarPorParticipante(Long idParticipante);
    Optional<Inscripcion> buscarPorParticipanteYEvento(Long idParticipante, Long idEvento);
    long contarInscritosEnEvento(Long idEvento);
    Optional<Inscripcion> buscarPorId(Long id);
}
