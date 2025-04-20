package com.grupo5.ms_participantes.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InscripcionDTO {
    private Long id;
    private String email;
    private String nombre;
    private Long idEvento;
    private String nombreEvento;
    private String lugarEvento;
    private LocalDateTime fechaInscripcion;
    private String estado;
}
