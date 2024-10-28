package com.example.petcare_api.service;

import com.example.petcare_api.entity.Cita;
import com.example.petcare_api.entity.ReporteCita;
import com.example.petcare_api.repository.CitaRepository;
import com.example.petcare_api.repository.ReporteCitaRepository;
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
