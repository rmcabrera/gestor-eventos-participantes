package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.dto.EventoDTO;
import com.grupo5.ms_participantes.feign.EventoFeignClient;

import org.springframework.stereotype.Service;

@Service
public class EventoServiceImpl implements EventoService {

    private final EventoFeignClient eventoFeignClient;

    public EventoServiceImpl(EventoFeignClient eventoFeignClient) {
        this.eventoFeignClient = eventoFeignClient;
    }

    @Override
    public EventoDTO buscarEventoPorId(Long id) {
        try {
            return eventoFeignClient.obtenerEventoPorId(id);
        } catch (feign.FeignException.NotFound e) {
            return null;
        }
    }
}