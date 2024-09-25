package com.example.gestion_citas.service;

import com.example.gestion_citas.entity.Cita;
import com.example.gestion_citas.entity.ReporteCita;
import com.example.gestion_citas.repository.CitaRepository;
import com.example.gestion_citas.repository.ReporteCitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteCitaService {

    @Autowired
    private ReporteCitaRepository reporteCitaRepository;

    @Autowired
    private CitaRepository citaRepository;

    public List<ReporteCita> obtenerTodosLosReportes() {
        return reporteCitaRepository.findAll();
    }

    public ReporteCita generarReporte(Integer idCita, String resumen) {
        Cita cita = citaRepository.findById(idCita).orElseThrow(() -> new RuntimeException("Cita no encontrada"));

        ReporteCita reporte = new ReporteCita();
        reporte.setCita(cita);
        reporte.setResumen(resumen);

        return reporteCitaRepository.save(reporte);
    }
}
