package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.entity.Inscripcion;
import com.grupo5.ms_participantes.feign.EventoRestClient;
import com.grupo5.ms_participantes.repository.InscripcionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;

    public InscripcionServiceImpl(InscripcionRepository inscripcionRepository, EventoRestClient eventoRestClient) {
        this.inscripcionRepository = inscripcionRepository;
    }

    @Override
    public Inscripcion guardar(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    @Override
    public List<Inscripcion> listarPorEvento(Long idEvento) {
        return inscripcionRepository.findByIdEvento(idEvento);
    }

    @Override
    public List<Inscripcion> listarPorParticipante(Long idParticipante) {
        return inscripcionRepository.findByParticipanteId(idParticipante);
    }

    @Override
    public Optional<Inscripcion> buscarPorParticipanteYEvento(Long idParticipante, Long idEvento) {
        return inscripcionRepository.findByParticipanteIdAndIdEvento(idParticipante, idEvento);
    }

    @Override
    public long contarInscritosEnEvento(Long idEvento) {
        return inscripcionRepository.countByIdEvento(idEvento);
    }

    @Override
    public Optional<Inscripcion> buscarPorId(Long id) {
        return inscripcionRepository.findById(id);
    }


}