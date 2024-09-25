package com.example.gestion_citas.controller;

import com.example.gestion_citas.entity.ReporteCita;
import com.example.gestion_citas.service.ReporteCitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTO para la solicitud de generación de reporte
class ReporteCitaDTO {
    private Integer idCita;
    private String resumen;

    // Getters y Setters
    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(Integer idCita) {
        this.idCita = idCita;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }
}

@RestController
@RequestMapping("/api/reportes")
public class ReporteCitaController {

    @Autowired
    private ReporteCitaService reporteCitaService;

    // Obtener todos los reportes
    @GetMapping
    public List<ReporteCita> obtenerTodosLosReportes() {
        return reporteCitaService.obtenerTodosLosReportes();
    }

    // Generar un nuevo reporte para una cita
    @PostMapping("/generar")
    public ReporteCita generarReporte(@RequestBody ReporteCitaDTO reporteCitaDTO) {
        return reporteCitaService.generarReporte(reporteCitaDTO.getIdCita(), reporteCitaDTO.getResumen());
    }
}
