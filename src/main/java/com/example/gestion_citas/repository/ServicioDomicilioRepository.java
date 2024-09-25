package com.example.gestion_citas.repository;

import com.example.gestion_citas.entity.ServicioDomicilio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioDomicilioRepository extends JpaRepository<ServicioDomicilio, Integer> {
    List<ServicioDomicilio> findByVeterinarioId(Integer veterinarioId); // Añadir este método
}
