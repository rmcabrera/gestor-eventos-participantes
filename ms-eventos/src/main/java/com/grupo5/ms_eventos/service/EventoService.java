package com.grupo5.ms_eventos.service;

import com.grupo5.ms_eventos.entity.Evento;

import java.util.List;
import java.util.Optional;

public interface EventoService {
    List<Evento> listar();
    Optional<Evento> buscarPorId(Long id);
    Evento guardar(Evento evento);
    boolean eliminar(Long id);
}
