package com.example.gestion_citas.controller;

import com.example.gestion_citas.entity.ServicioDomicilio;
import com.example.gestion_citas.service.ServicioDomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicios-domicilio")
public class ServicioDomicilioController {

    @Autowired
    private ServicioDomicilioService servicioDomicilioService;

    @GetMapping
    public List<ServicioDomicilio> obtenerServicios() {
        return servicioDomicilioService.obtenerServicios();
    }

    @PostMapping
    public ResponseEntity<ServicioDomicilio> programarServicio(@RequestBody ServicioDomicilio servicio) {
        ServicioDomicilio nuevoServicio = servicioDomicilioService.programarServicio(servicio);
        return ResponseEntity.ok(nuevoServicio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServicioDomicilio> actualizarServicio(@PathVariable Integer id, @RequestBody ServicioDomicilio servicio) {
        ServicioDomicilio servicioActualizado = servicioDomicilioService.actualizarServicio(id, servicio);
        return ResponseEntity.ok(servicioActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarServicio(@PathVariable Integer id) {
        servicioDomicilioService.cancelarServicio(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/veterinario/{veterinarioId}")
    public List<ServicioDomicilio> obtenerServiciosPorVeterinario(@PathVariable Integer veterinarioId) {
        return servicioDomicilioService.obtenerServiciosPorVeterinarioId(veterinarioId); // Llama al método correcto
    }
}
