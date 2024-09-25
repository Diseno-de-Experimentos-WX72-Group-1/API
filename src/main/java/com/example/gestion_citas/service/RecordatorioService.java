package com.example.gestion_citas.service;

import com.example.gestion_citas.entity.Recordatorio;
import com.example.gestion_citas.repository.RecordatorioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordatorioService {

    @Autowired
    private RecordatorioRepository recordatorioRepository;

    // Obtener todos los recordatorios de una mascota específica
    public List<Recordatorio> obtenerRecordatoriosPorMascota(Integer idMascota) {
        return recordatorioRepository.findByMascotaId(idMascota);
    }

    // Crear un nuevo recordatorio
    public Recordatorio crearRecordatorio(Recordatorio recordatorio) {
        return recordatorioRepository.save(recordatorio);
    }
}
