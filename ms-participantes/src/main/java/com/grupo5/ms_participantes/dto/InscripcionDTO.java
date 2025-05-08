package com.grupo5.ms_participantes.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InscripcionDTO {
    private Long id;
    private String email;
    private String nombre;
    private Long idEvento;
    private String nombreEvento;
    private String lugarEvento;
    private LocalDate fechaInscripcion;
    private String estado;
}
