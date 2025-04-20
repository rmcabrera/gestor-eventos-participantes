package com.grupo5.ms_eventos.service;

import com.grupo5.ms_eventos.entity.Evento;
import com.grupo5.ms_eventos.repository.EventoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public List<Evento> listar() {
        return eventoRepository.findAll();
    }

    @Override
    public Optional<Evento> buscarPorId(Long id) {
        return eventoRepository.findById(id);
    }

    @Override
    @Transactional
    public Evento guardar(Evento evento) {
        return eventoRepository.save(evento);
    }

    @Override
    public boolean eliminar(Long id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}