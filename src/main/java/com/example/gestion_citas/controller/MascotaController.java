package com.example.gestion_citas.controller;

import com.example.gestion_citas.entity.Mascota;
import com.example.gestion_citas.service.MascotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public List<Mascota> obtenerMascotas() {
        return mascotaService.obtenerMascotas();
    }

    @PostMapping("/registrar")
    public ResponseEntity<Mascota> registrarMascota(@RequestBody Mascota mascota) {
        return new ResponseEntity<>(mascotaService.registrarMascota(mascota), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizarMascota(@PathVariable Integer id, @RequestBody Mascota mascota) {
        return new ResponseEntity<>(mascotaService.actualizarMascota(id, mascota), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMascota(@PathVariable Integer id) {
        mascotaService.eliminarMascota(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mascota> obtenerMascotaPorId(@PathVariable Integer id) {
        Mascota mascota = mascotaService.obtenerMascotaPorId(id);
        return new ResponseEntity<>(mascota, HttpStatus.OK);
    }
}
