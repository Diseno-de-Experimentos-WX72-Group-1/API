package com.example.gestion_citas.controller;

import com.example.gestion_citas.entity.HistorialMedico;
import com.example.gestion_citas.repository.HistorialMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial-medico")
public class HistorialMedicoController {

    @Autowired
    private HistorialMedicoRepository historialMedicoRepository;

    // Obtener el historial médico de una mascota
    @GetMapping("/mascota/{idMascota}")
    public ResponseEntity<List<HistorialMedico>> getHistorialPorMascota(@PathVariable Integer idMascota) {
        List<HistorialMedico> historial = historialMedicoRepository.findByMascotaId(idMascota);
        return ResponseEntity.ok(historial);
    }

    // Obtener el historial médico consultado por un veterinario
    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<List<HistorialMedico>> getHistorialPorVeterinario(@PathVariable Integer idVeterinario) {
        List<HistorialMedico> historial = historialMedicoRepository.findByVeterinarioId(idVeterinario);
        return ResponseEntity.ok(historial);
    }
}
