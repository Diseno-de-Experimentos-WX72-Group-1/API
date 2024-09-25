package com.example.gestion_citas.service;

import com.example.gestion_citas.entity.ServicioDomicilio;
import com.example.gestion_citas.repository.ServicioDomicilioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServicioDomicilioService {

    @Autowired
    private ServicioDomicilioRepository repository; // Mantén solo una instancia

    public List<ServicioDomicilio> obtenerServicios() {
        return repository.findAll();
    }

    public ServicioDomicilio programarServicio(ServicioDomicilio servicio) {
        servicio.setFechaSolicitud(LocalDate.now()); // Establece la fecha de solicitud como la fecha actual
        return repository.save(servicio);
    }

    public ServicioDomicilio actualizarServicio(Integer id, ServicioDomicilio servicio) {
        servicio.setIdServicio(id);
        return repository.save(servicio);
    }

    public void cancelarServicio(Integer id) {
        repository.deleteById(id);
    }

    public List<ServicioDomicilio> obtenerServiciosPorVeterinarioId(Integer veterinarioId) {
        // Usa la instancia del repositorio para llamar al método
        return repository.findByVeterinarioId(veterinarioId);
    }
}
