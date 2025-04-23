package com.example.nrs_usuario.repository;

import com.example.nrs_usuario.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email); // <--- Verifica esta línea
    List<Usuario> findByTipo(String tipo);
}