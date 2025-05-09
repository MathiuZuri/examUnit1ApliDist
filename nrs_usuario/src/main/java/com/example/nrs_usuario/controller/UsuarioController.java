package com.example.nrs_usuario.controller;

import com.example.nrs_usuario.entity.Usuario;
import com.example.nrs_usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios") // Ruta base para los usuarios
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService; // Inyectamos UsuarioService

    @GetMapping()
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Usuario> actualizarUsuario(@RequestBody Usuario usuario) {
        if (usuarioService.listarPorId(usuario.getId()).isPresent()) {
            return new ResponseEntity<>(usuarioService.actualizar(usuario), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Integer id) {
        return usuarioService.listarPorId(id)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuarioPorId(@PathVariable(required = true) Integer id) {
        if (usuarioService.listarPorId(id).isPresent()) {
            usuarioService.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoints adicionales basados en los métodos en UsuarioService

    @GetMapping("/email/{email}")
    public ResponseEntity<Usuario> obtenerUsuarioPorEmail(@PathVariable String email) {
        Optional<Usuario> usuarioOptional = usuarioService.buscarPorEmail(email);
        return usuarioOptional.map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Usuario>> listarUsuariosPorTipo(@PathVariable String tipo) {
        List<Usuario> usuarios = usuarioService.listarPorTipo(tipo);
        if (!usuarios.isEmpty()) {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Usuario>> listarUsuariosPorEstado(@PathVariable String estado) {
        List<Usuario> usuarios = usuarioService.listarPorEstado(estado);
        if (!usuarios.isEmpty()) {
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}