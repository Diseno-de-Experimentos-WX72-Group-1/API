package com.example.gestion_citas.service;

import com.example.gestion_citas.entity.Cita;
import com.example.gestion_citas.entity.Mascota;
import com.example.gestion_citas.entity.Usuario;
import com.example.gestion_citas.exception.ResourceNotFoundException;
import com.example.gestion_citas.repository.CitaRepository;
import com.example.gestion_citas.repository.MascotaRepository;
import com.example.gestion_citas.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class CitaServiceTest {

    @Mock
    private CitaRepository citaRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private MascotaRepository mascotaRepository;

    @InjectMocks
    private CitaService citaService;

    private Cita cita;
    private Usuario veterinario;
    private Mascota mascota;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Creación de objetos simulados
        veterinario = new Usuario();
        veterinario.setId(1);

        mascota = new Mascota();
        mascota.setId(1);

        cita = new Cita();
        cita.setId(1);
        cita.setMascota(mascota);
        cita.setVeterinario(veterinario);
        cita.setEstado(Cita.Estado.PENDIENTE);
    }

    @Test
    public void testProgramarCita_Correcto() {
        // Simular el comportamiento de los repositorios
        when(mascotaRepository.findById(mascota.getId())).thenReturn(Optional.of(mascota));
        when(usuarioRepository.findById(veterinario.getId())).thenReturn(Optional.of(veterinario));
        when(citaRepository.save(cita)).thenReturn(cita);

        // Llamada al método
        Cita result = citaService.programarCita(cita);

        // Verificaciones
        assertNotNull(result);
        assertEquals(Cita.Estado.PENDIENTE, result.getEstado());
        verify(citaRepository, times(1)).save(cita);
    }

    @Test
    public void testProgramarCita_MascotaNoEncontrada() {
        // Simular excepción de mascota no encontrada
        when(mascotaRepository.findById(mascota.getId())).thenReturn(Optional.empty());

        // Verificar que se lanza la excepción
        assertThrows(ResourceNotFoundException.class, () -> citaService.programarCita(cita));
    }

    @Test
    public void testObtenerCitasPorVeterinario() {
        // Crear lista simulada de citas
        List<Cita> citas = new ArrayList<>();
        citas.add(cita);

        when(citaRepository.findByVeterinario_Id(veterinario.getId())).thenReturn(citas);

        // Llamada al método
        List<Cita> result = citaService.obtenerCitasPorVeterinario(veterinario.getId());

        // Verificaciones
        assertEquals(1, result.size());
        verify(citaRepository, times(1)).findByVeterinario_Id(veterinario.getId());
    }

    @Test
    public void testActualizarCita_Correcto() {
        // Simular el comportamiento de los repositorios
        when(citaRepository.findById(cita.getId())).thenReturn(Optional.of(cita));
        when(mascotaRepository.findById(mascota.getId())).thenReturn(Optional.of(mascota));
        when(usuarioRepository.findById(veterinario.getId())).thenReturn(Optional.of(veterinario));

        // Llamada al método
        Cita result = citaService.actualizarCita(cita);

        // Verificaciones
        assertNotNull(result);
        verify(citaRepository, times(1)).save(cita);
    }

    @Test
    public void testCancelarCita() {
        // Simular el comportamiento de los repositorios
        when(citaRepository.findById(cita.getId())).thenReturn(Optional.of(cita));

        // Llamada al método
        citaService.cancelarCita(cita.getId());

        // Verificaciones
        assertEquals(Cita.Estado.CANCELADA, cita.getEstado());
        verify(citaRepository, times(1)).save(cita);
    }

    @Test
    public void testCompletarCita() {
        // Simular el comportamiento de los repositorios
        when(citaRepository.findById(cita.getId())).thenReturn(Optional.of(cita));

        // Llamada al método
        Cita result = citaService.completarCita(cita.getId());

        // Verificaciones
        assertNotNull(result);
        assertEquals(Cita.Estado.COMPLETADA, result.getEstado());
        verify(citaRepository, times(1)).save(cita);
    }

    @Test
    public void testCompletarCita_CitaNoEncontrada() {
        // Simular comportamiento cuando no se encuentra la cita
        when(citaRepository.findById(cita.getId())).thenReturn(Optional.empty());

        // Llamada al método
        Cita result = citaService.completarCita(cita.getId());

        // Verificaciones
        assertNull(result);
        verify(citaRepository, never()).save(any(Cita.class));
    }

    @Test
    public void testObtenerTodasLasCitas() {
        // Crear lista simulada de citas
        List<Cita> citas = new ArrayList<>();
        citas.add(cita);

        when(citaRepository.findAll()).thenReturn(citas);

        // Llamada al método
        List<Cita> result = citaService.obtenerTodasLasCitas();

        // Verificaciones
        assertEquals(1, result.size());
        verify(citaRepository, times(1)).findAll();
    }
}