package com.example.nrs_usuario.service;

import com.example.nrs_usuario.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    List<Usuario> listar();

    Usuario guardar(Usuario usuario);

    Usuario actualizar(Usuario usuario);

    Optional<Usuario> listarPorId(Integer id);

    void eliminarPorId(Integer id);

    // Puedes agregar métodos específicos para Usuario si los necesitas,
    // por ejemplo, buscar por email, listar por tipo, etc.
    Optional<Usuario> buscarPorEmail(String email);
    List<Usuario> listarPorTipo(String tipo);
}