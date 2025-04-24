package com.example.nrs_usuario.service.impl;

import com.example.nrs_usuario.entity.Usuario;
import com.example.nrs_usuario.repository.UsuarioRepository;
import com.example.nrs_usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> listarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public Optional<Usuario> buscarPorEstado(String estado) {
        return usuarioRepository.findByEstado(estado);
    }

    @Override
    public List<Usuario> listarPorTipo(String tipo) {
        return usuarioRepository.findByTipo(tipo);
    }

    @Override
    public List<Usuario> listarPorEstado(String estado) {
        return usuarioRepository.findByTipo(estado);
    }


}