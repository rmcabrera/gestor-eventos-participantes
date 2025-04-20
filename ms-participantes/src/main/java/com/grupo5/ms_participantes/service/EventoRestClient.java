package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.dto.EventoDTO;

public interface EventoRestClient {
    EventoDTO obtenerEventoPorId(Long id);
}
