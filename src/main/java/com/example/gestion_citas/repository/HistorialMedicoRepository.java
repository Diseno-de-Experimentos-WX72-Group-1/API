package com.example.gestion_citas.repository;

import com.example.gestion_citas.entity.HistorialMedico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistorialMedicoRepository extends JpaRepository<HistorialMedico, Integer> {
    List<HistorialMedico> findByMascotaId(Integer idMascota);
    List<HistorialMedico> findByVeterinarioId(Integer idVeterinario);
}
