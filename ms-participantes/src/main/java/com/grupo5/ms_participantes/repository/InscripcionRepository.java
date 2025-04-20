package com.grupo5.ms_participantes.repository;

import com.grupo5.ms_participantes.entity.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByIdEvento(Long idEvento);
    List<Inscripcion> findByParticipanteId(Long participanteId);
    Optional<Inscripcion> findByParticipanteIdAndIdEvento(Long participanteId, Long idEvento);
    Long countByIdEvento(Long idEvento);
}
