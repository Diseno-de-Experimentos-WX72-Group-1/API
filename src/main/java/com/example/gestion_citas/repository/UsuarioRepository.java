package com.example.gestion_citas.repository;

import com.example.gestion_citas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    
    List<Usuario> findByRol(Usuario.Rol rol); // Cambiar a Rol
    Usuario findByCorreo(String correo);
    //List<Usuario> findByRol(String rol); // Asegúrate de que este método esté presente
}
