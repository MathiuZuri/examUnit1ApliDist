package com.example.ms_catalogo.service.impl;

import com.example.ms_catalogo.entity.Libro;
import com.example.ms_catalogo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros") // Cambiamos la ruta base a /libros
public class LibroController {
    @Autowired
    private LibroService libroService; // Inyectamos LibroService

    @GetMapping()
    public ResponseEntity<List<Libro>> list() { // Cambiamos a Libro
        return new ResponseEntity<>(libroService.listar(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Libro> save(@RequestBody Libro libro) { // Cambiamos a Libro
        return new ResponseEntity<>(libroService.guardar(libro), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Libro> update(@RequestBody Libro libro) { // Cambiamos a Libro
        if (libroService.listarPorId(libro.getId()).isPresent()) {
            return new ResponseEntity<>(libroService.actualizar(libro), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> listById(@PathVariable(required = true) Integer id) { // Cambiamos a Libro
        return libroService.listarPorId(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK)) // Cambiamos a Libro
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(required = true) Integer id) {
        if (libroService.listarPorId(id).isPresent()) {
            libroService.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/reducir-stock") // Nuevo endpoint para reducir stock
    public ResponseEntity<?> reducirStock(@PathVariable Integer id, @RequestParam Integer cantidad) {
        Optional<Libro> libroOptional = libroService.listarPorId(id);
        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            if (libro.getStock() >= cantidad) {
                libroService.reducirStock(id, cantidad);
                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().body("No hay suficiente stock.");
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}