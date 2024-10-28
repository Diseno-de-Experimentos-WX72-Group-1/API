package com.example.petcare_api.service;

import com.example.petcare_api.entity.Recordatorio;
import com.example.petcare_api.repository.RecordatorioRepository;
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
