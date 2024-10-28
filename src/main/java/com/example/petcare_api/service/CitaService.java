package com.example.petcare_api.service;

import com.example.petcare_api.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.petcare_api.entity.Cita;
import com.example.petcare_api.entity.Mascota;
import com.example.petcare_api.repository.CitaRepository;
import com.example.petcare_api.repository.UsuarioRepository;
import com.example.petcare_api.repository.MascotaRepository;
import com.example.petcare_api.exception.ResourceNotFoundException;

@Service
public class CitaService {

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MascotaRepository mascotaRepository; 

    public Cita programarCita(Cita cita) {
        Mascota mascota = mascotaRepository.findById(cita.getMascota().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada"));

        Usuario veterinario = obtenerVeterinario(cita.getVeterinario().getId());

        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setEstado(Cita.Estado.PENDIENTE); 

        return citaRepository.save(cita);
    }

    public List<Cita> obtenerCitasPorVeterinario(Integer idVeterinario) {
        return citaRepository.findByVeterinario_Id(idVeterinario);
    }

    public Cita actualizarCita(Cita cita) {
        Cita citaExistente = citaRepository.findById(cita.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Cita no encontrada con id: " + cita.getId()));

        Mascota mascota = verificarMascota(cita.getMascota().getId());
        Usuario veterinario = verificarVeterinario(cita.getVeterinario().getId());

        actualizarDatosCita(citaExistente, cita, mascota, veterinario);

        return citaRepository.save(citaExistente);
    }

    public void cancelarCita(Integer idCita) {
        citaRepository.findById(idCita).ifPresent(cita -> {
            cita.setEstado(Cita.Estado.CANCELADA);
            citaRepository.save(cita);
        });
    }

    public Cita completarCita(Integer idCita) {
        return citaRepository.findById(idCita)
                .map(cita -> {
                    cita.setEstado(Cita.Estado.COMPLETADA);
                    return citaRepository.save(cita);
                }).orElse(null);
    }

    public List<Cita> obtenerTodasLasCitas() {
        return citaRepository.findAll();
    }

    // Métodos auxiliares
    private Usuario obtenerVeterinario(Integer idVeterinario) {
        return usuarioRepository.findById(idVeterinario)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con id: " + idVeterinario));
    }

    private Mascota verificarMascota(Integer idMascota) {
        return mascotaRepository.findById(idMascota)
                .orElseThrow(() -> new ResourceNotFoundException("Mascota no encontrada con id: " + idMascota));
    }

    private Usuario verificarVeterinario(Integer idVeterinario) {
        return usuarioRepository.findById(idVeterinario)
                .orElseThrow(() -> new ResourceNotFoundException("Veterinario no encontrado con id: " + idVeterinario));
    }

    private void actualizarDatosCita(Cita citaExistente, Cita cita, Mascota mascota, Usuario veterinario) {
        citaExistente.setFechaCita(cita.getFechaCita());
        citaExistente.setHoraCita(cita.getHoraCita());
        citaExistente.setMotivo(cita.getMotivo());
        citaExistente.setMascota(mascota);
        citaExistente.setVeterinario(veterinario);
        citaExistente.setEstado(cita.getEstado());
    }
}
