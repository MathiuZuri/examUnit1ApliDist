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
    Optional<Usuario> buscarPorEmail(String email);

    Optional<Usuario> buscarPorEstado(String estado);

    List<Usuario> listarPorTipo(String tipo);
    List<Usuario> listarPorEstado(String estado);

}