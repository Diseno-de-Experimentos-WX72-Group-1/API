package com.example.petcare_api.controller;

import com.example.petcare_api.dto.LoginRequest;
import com.example.petcare_api.entity.Usuario;
import com.example.petcare_api.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioControllerTest {

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private UsuarioController usuarioController;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicialización de un objeto usuario para las pruebas
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNombre("Juan");
        usuario.setCorreo("juan@example.com");
        usuario.setContraseña("password");
        usuario.setRol(Usuario.Rol.veterinario);
    }

    @Test
    public void testRegistrarUsuario() {
        when(usuarioService.registrarUsuario(any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.registrarUsuario(usuario);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());
        verify(usuarioService, times(1)).registrarUsuario(any(Usuario.class));
    }

    @Test
    public void testIniciarSesion_Exito() {
        when(usuarioService.iniciarSesion("juan@example.com", "password")).thenReturn(usuario);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo("juan@example.com");
        loginRequest.setContraseña("password");

        // Llamada al método
        ResponseEntity<Object> response = usuarioController.iniciarSesion(loginRequest);

        // Verificaciones
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof Usuario);
        verify(usuarioService, times(1)).iniciarSesion("juan@example.com", "password");
    }

    @Test
    public void testIniciarSesion_Fallo() {
        when(usuarioService.iniciarSesion("juan@example.com", "password")).thenReturn(null);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setCorreo("juan@example.com");
        loginRequest.setContraseña("password");

        ResponseEntity<Object> response = usuarioController.iniciarSesion(loginRequest);

        // Verificaciones
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Credenciales inválidas", response.getBody());
        verify(usuarioService, times(1)).iniciarSesion("juan@example.com", "password");
    }

    @Test
    public void testObtenerUsuarios() {
        when(usuarioService.obtenerUsuarios()).thenReturn(Arrays.asList(usuario));

        List<Usuario> result = usuarioController.obtenerUsuarios();

        assertEquals(1, result.size());
        assertEquals("Juan", result.get(0).getNombre());
        verify(usuarioService, times(1)).obtenerUsuarios();
    }

    @Test
    public void testObtenerUsuarioPorId_Exito() {
        when(usuarioService.obtenerUsuarioPorId(1)).thenReturn(Optional.of(usuario));

        ResponseEntity<Usuario> response = usuarioController.obtenerUsuarioPorId(1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());
        verify(usuarioService, times(1)).obtenerUsuarioPorId(1);
    }

    @Test
    public void testObtenerUsuarioPorId_NoEncontrado() {
        when(usuarioService.obtenerUsuarioPorId(1)).thenReturn(Optional.empty());

        ResponseEntity<Usuario> response = usuarioController.obtenerUsuarioPorId(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(usuarioService, times(1)).obtenerUsuarioPorId(1);
    }

    @Test
    public void testCrearUsuario() {
        when(usuarioService.crearUsuario(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioController.crearUsuario(usuario);

        assertNotNull(result);
        assertEquals("Juan", result.getNombre());
        verify(usuarioService, times(1)).crearUsuario(any(Usuario.class));
    }

    @Test
    public void testActualizarUsuario_Exito() {
        when(usuarioService.actualizarUsuario(eq(1), any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(1, usuario);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Juan", response.getBody().getNombre());
        verify(usuarioService, times(1)).actualizarUsuario(eq(1), any(Usuario.class));
    }

    @Test
    public void testActualizarUsuario_NoEncontrado() {
        when(usuarioService.actualizarUsuario(eq(1), any(Usuario.class))).thenReturn(null);

        ResponseEntity<Usuario> response = usuarioController.actualizarUsuario(1, usuario);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(usuarioService, times(1)).actualizarUsuario(eq(1), any(Usuario.class));
    }

    @Test
    public void testEliminarUsuario_Exito() {
        when(usuarioService.eliminarUsuario(1)).thenReturn(true);

        ResponseEntity<Void> response = usuarioController.eliminarUsuario(1);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(usuarioService, times(1)).eliminarUsuario(1);
    }

    @Test
    public void testEliminarUsuario_NoEncontrado() {
        when(usuarioService.eliminarUsuario(1)).thenReturn(false);

        ResponseEntity<Void> response = usuarioController.eliminarUsuario(1);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(usuarioService, times(1)).eliminarUsuario(1);
    }
}