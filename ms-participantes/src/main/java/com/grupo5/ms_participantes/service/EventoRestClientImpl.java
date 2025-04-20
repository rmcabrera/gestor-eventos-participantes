package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.config.EventosProperties;
import com.grupo5.ms_participantes.dto.EventoDTO;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EventoRestClientImpl implements EventoRestClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private final EventosProperties eventosProperties;

    public EventoRestClientImpl(EventosProperties eventosProperties) {
        this.eventosProperties = eventosProperties;
    }

    @Override
    public EventoDTO obtenerEventoPorId(Long id) {
        String urlBase = eventosProperties.getUrl();
        try {
            return restTemplate.getForObject(urlBase + "/" + id, EventoDTO.class);
        } catch (org.springframework.web.client.HttpClientErrorException.NotFound e) {
            return null;
        }
    }

}