package com.example.gestion_citas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import com.example.gestion_citas.entity.Usuario;
import com.example.gestion_citas.repository.UsuarioRepository;

import java.util.Optional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario iniciarSesion(String correo, String contraseña) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null && usuario.getContraseña().equals(contraseña)) {
            return usuario; // Devuelve el usuario si las credenciales son correctas
        }
        return null; // Devuelve null si las credenciales son incorrectas
    }


    public List<Usuario> obtenerVeterinarios() {
    return usuarioRepository.findByRol(Usuario.Rol.veterinario); // Cambia a Rol
    }


    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(Integer id, Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setContraseña(usuarioActualizado.getContraseña());
            usuarioExistente.setRol(usuarioActualizado.getRol());
            return usuarioRepository.save(usuarioExistente);
        }
        return null;
    }

    public boolean eliminarUsuario(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        if (usuarioOpt.isPresent()) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
