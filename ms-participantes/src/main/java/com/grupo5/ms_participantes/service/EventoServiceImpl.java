package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.dto.EventoDTO;
import com.grupo5.ms_participantes.feign.EventoRestClient;

import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoRestClient eventoRestClient;

    public EventoServiceImpl(EventoRestClient eventoRestClient) {
        this.eventoRestClient = eventoRestClient;
    }

    @Override
    public EventoDTO buscarEventoPorId(Long id) {
        try {
            return eventoRestClient.obtenerEventoPorId(id);
        } catch (feign.FeignException.NotFound e) {
            return null;
        }
    }
}