package com.grupo5.ms_eventos.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "evento")
@Data
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fecha_inicio", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate  fechaInicio;

    @Column(name = "fecha_fin")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate  fechaFin;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "cupo_maximo")
    private Integer cupoMaximo;

    @Column(name = "creado_en", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime creadoEn;

}
