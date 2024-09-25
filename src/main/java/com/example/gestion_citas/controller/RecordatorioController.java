package com.example.gestion_citas.controller;

import com.example.gestion_citas.entity.Recordatorio;
import com.example.gestion_citas.service.RecordatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recordatorios")
public class RecordatorioController {

    @Autowired
    private RecordatorioService recordatorioService;

    // Obtener recordatorios por mascota
    @GetMapping("/{idMascota}")
    public List<Recordatorio> obtenerRecordatoriosPorMascota(@PathVariable Integer idMascota) {
        return recordatorioService.obtenerRecordatoriosPorMascota(idMascota);
    }

    // Crear un nuevo recordatorio
    @PostMapping("/crear")
    public Recordatorio crearRecordatorio(@RequestBody Recordatorio recordatorio) {
        return recordatorioService.crearRecordatorio(recordatorio);
    }
}
