package com.example.petcare_api.service;

import com.example.petcare_api.entity.Usuario;
import com.example.petcare_api.repository.UsuarioRepository;
import com.example.petcare_api.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");
        usuario.setCorreo("juan@example.com");
        usuario.setContraseña("password");
        usuario.setRol(Usuario.Rol.veterinario);
    }

    @Test
    public void testRegistrarUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.registrarUsuario(usuario);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testIniciarSesion_Exito() {
        when(usuarioRepository.findByCorreo("juan@example.com")).thenReturn(usuario);

        Usuario result = usuarioService.iniciarSesion("juan@example.com", "password");

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
    }

    @Test
    public void testIniciarSesion_Fallo() {
        when(usuarioRepository.findByCorreo("juan@example.com")).thenReturn(usuario);

        Usuario result = usuarioService.iniciarSesion("juan@example.com", "wrongpassword");

        assertNull(result);
    }

    @Test
    public void testObtenerVeterinarios() {
        when(usuarioRepository.findByRol(Usuario.Rol.veterinario)).thenReturn(Arrays.asList(usuario));

        List<Usuario> result = usuarioService.obtenerVeterinarios();

        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findByRol(Usuario.Rol.veterinario);
    }

    @Test
    public void testObtenerUsuarios() {
        when(usuarioRepository.findAll()).thenReturn(Arrays.asList(usuario));

        List<Usuario> result = usuarioService.obtenerUsuarios();

        assertEquals(1, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerUsuarioPorId() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        Optional<Usuario> result = usuarioService.obtenerUsuarioPorId(1);

        assertTrue(result.isPresent());
        assertEquals("Juan", result.get().getNombre());
    }

    @Test
    public void testCrearUsuario() {
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.crearUsuario(usuario);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testActualizarUsuario_Exito() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario usuarioActualizado = new Usuario();
        usuarioActualizado.setNombre("Juan Actualizado");
        usuarioActualizado.setCorreo("juan.actualizado@example.com");
        usuarioActualizado.setContraseña("newpassword");
        usuarioActualizado.setRol(Usuario.Rol.dueno);

        Usuario result = usuarioService.actualizarUsuario(1, usuarioActualizado);

        assertNotNull(result);
        assertEquals("Juan Actualizado", result.getNombre());
        assertEquals(Usuario.Rol.dueno, result.getRol());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testActualizarUsuario_NoEncontrado() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        Usuario result = usuarioService.actualizarUsuario(1, usuario);

        assertNull(result);
    }

    @Test
    public void testEliminarUsuario_Exito() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.of(usuario));

        boolean result = usuarioService.eliminarUsuario(1);

        assertTrue(result);
        verify(usuarioRepository, times(1)).deleteById(1);
    }

    @Test
    public void testEliminarUsuario_NoEncontrado() {
        when(usuarioRepository.findById(1)).thenReturn(Optional.empty());

        boolean result = usuarioService.eliminarUsuario(1);

        assertFalse(result);
        verify(usuarioRepository, never()).deleteById(anyInt());
    }
}