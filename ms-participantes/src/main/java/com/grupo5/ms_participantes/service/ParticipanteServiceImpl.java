package com.grupo5.ms_participantes.service;

import com.grupo5.ms_participantes.entity.Participante;
import com.grupo5.ms_participantes.repository.ParticipanteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParticipanteServiceImpl implements ParticipanteService {

    private final ParticipanteRepository participanteRepository;

    public ParticipanteServiceImpl(ParticipanteRepository participanteRepository) {
        this.participanteRepository = participanteRepository;
    }

    @Override
    public Participante guardar(Participante participante) {
        if (participanteRepository.findByEmail(participante.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya está registrado");
        }
        return participanteRepository.save(participante);
    }

    @Override
    public Optional<Participante> buscarPorEmail(String email) {
        return participanteRepository.findByEmail(email);
    }

    @Override
    public Participante actualizar(Long id, Participante participante) {
        return participanteRepository.findById(id)
                .map(existing -> {
                    existing.setNombres(participante.getNombres());
                    existing.setApellidos(participante.getApellidos());
                    existing.setEmail(participante.getEmail());
                    existing.setTelefono(participante.getTelefono());
                    return participanteRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));
    }

    @Override
    public void eliminar(Long id) {
        if (!participanteRepository.existsById(id)) {
            throw new RuntimeException("Participante no encontrado");
        }
        participanteRepository.deleteById(id);
    }

    @Override
    public Optional<Participante> buscarPorId(Long id) {
        return participanteRepository.findById(id);
    }

    @Override
    public List<Participante> listarTodos() {
        return participanteRepository.findAll();
    }
}
