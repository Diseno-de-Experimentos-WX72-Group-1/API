package com.example.gestion_citas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestion_citas.entity.Cita;
import com.example.gestion_citas.entity.Mascota;  // Importar la clase Mascota
import com.example.gestion_citas.entity.Usuario;   // Importar la clase Usuario
import com.example.gestion_citas.repository.MascotaRepository; // Asegúrate de importar el repositorio
import com.example.gestion_citas.repository.UsuarioRepository; // Asegúrate de importar el repositorio
import com.example.gestion_citas.service.CitaService;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
public class CitaController {
    @Autowired
    private CitaService citaService;

    @Autowired
    private MascotaRepository mascotaRepository; // Inyección del repositorio de Mascota

    @Autowired
    private UsuarioRepository usuarioRepository; // Inyección del repositorio de Usuario

    @PostMapping("/programar")
    public ResponseEntity<?> programarCita(@RequestBody Cita cita) {
        try {
            Cita nuevaCita = citaService.programarCita(cita);
            return ResponseEntity.ok(nuevaCita);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al programar la cita: " + e.getMessage());
        }
    }

    @GetMapping("/veterinario/{idVeterinario}")
    public ResponseEntity<List<Cita>> obtenerCitasPorVeterinario(@PathVariable Integer idVeterinario) {
        List<Cita> citas = citaService.obtenerCitasPorVeterinario(idVeterinario);
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<Cita> actualizarCita(@RequestBody Cita cita) {
        Cita citaActualizada = citaService.actualizarCita(cita);
        return ResponseEntity.ok(citaActualizada);
    }

    @PutMapping("/cancelar/{idCita}")
    public ResponseEntity<Void> cancelarCita(@PathVariable Integer idCita) {
        citaService.cancelarCita(idCita);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/completar/{idCita}")
    public ResponseEntity<Cita> completarCita(@PathVariable Integer idCita) {
        Cita citaCompletada = citaService.completarCita(idCita);
        return ResponseEntity.ok(citaCompletada);
    }

    @GetMapping
    public ResponseEntity<List<Cita>> obtenerCitas() {
        List<Cita> citas = citaService.obtenerTodasLasCitas();
        return ResponseEntity.ok(citas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarCita(@PathVariable Integer id, @RequestBody Cita cita) {
        try {
            cita.setId(id);
            Cita citaActualizada = citaService.actualizarCita(cita);
            return ResponseEntity.ok(citaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error al actualizar la cita: " + e.getMessage());
        }
    }

    @GetMapping("/mascotas")
    public ResponseEntity<List<Mascota>> obtenerMascotas() {
        List<Mascota> mascotas = mascotaRepository.findAll();
        return ResponseEntity.ok(mascotas);
    }

      @GetMapping("/usuarios/veterinarios")
        public ResponseEntity<?> obtenerVeterinarios() {
            try {
                List<Usuario> veterinarios = usuarioRepository.findByRol(Usuario.Rol.veterinario); // Llamar correctamente
                return ResponseEntity.ok(veterinarios);
            } catch (Exception e) {
                System.err.println("Error en obtenerVeterinarios: " + e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body("Error al obtener veterinarios: " + e.getMessage());
            }
        }

}
