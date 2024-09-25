package com.example.gestion_citas.repository;

import com.example.gestion_citas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByVeterinario_Id(Integer idVeterinario);
}
