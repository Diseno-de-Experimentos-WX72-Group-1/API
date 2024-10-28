package com.example.petcare_api.repository;

import com.example.petcare_api.entity.Recordatorio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordatorioRepository extends JpaRepository<Recordatorio, Integer> {
    // Obtener recordatorios por mascota
    List<Recordatorio> findByMascotaId(Integer idMascota);
}
