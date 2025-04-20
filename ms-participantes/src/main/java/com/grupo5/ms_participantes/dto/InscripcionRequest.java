package com.grupo5.ms_participantes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InscripcionRequest {
    private String email;
    private String nombres;
    private String apellidos;
    private String telefono;
    private Long idEvento;
}

