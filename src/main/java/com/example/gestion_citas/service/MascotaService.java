package com.example.gestion_citas.service;

import com.example.gestion_citas.entity.Mascota;
import com.example.gestion_citas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    // Obtener todas las mascotas
    public List<Mascota> obtenerMascotas() {
        return mascotaRepository.findAll();
    }

    // Registrar una nueva mascota
    public Mascota registrarMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    // Actualizar una mascota existente
    public Mascota actualizarMascota(Integer id, Mascota mascota) {
        // Verifica si la mascota existe
        Optional<Mascota> mascotaExistente = mascotaRepository.findById(id);
        if (mascotaExistente.isPresent()) {
            mascota.setId(id); // Establecer el ID para la actualización
            return mascotaRepository.save(mascota); // Guarda y retorna la mascota actualizada
        }
        return null; // Puede lanzar una excepción si no existe
    }

    // Eliminar una mascota por ID
    public void eliminarMascota(Integer id) {
        mascotaRepository.deleteById(id);
    }

    // Obtener una mascota por ID
    public Mascota obtenerMascotaPorId(Integer id) {
        return mascotaRepository.findById(id).orElse(null);
    }
}
