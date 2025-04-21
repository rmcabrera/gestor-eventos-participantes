package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.dto.EventoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-eventos", url = "${ms.eventos.url}")
public interface EventoRestClient {

    @GetMapping("/{id}")
    EventoDTO obtenerEventoPorId(@PathVariable("id") Long id);
}
