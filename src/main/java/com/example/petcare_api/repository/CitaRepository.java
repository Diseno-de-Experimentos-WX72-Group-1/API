package com.example.petcare_api.repository;

import com.example.petcare_api.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CitaRepository extends JpaRepository<Cita, Integer> {
    List<Cita> findByVeterinario_Id(Integer idVeterinario);
}
