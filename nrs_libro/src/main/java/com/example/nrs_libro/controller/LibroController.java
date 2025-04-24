package com.example.nrs_libro.controller;

import com.example.nrs_libro.entity.Libro;
import com.example.nrs_libro.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping()
    public ResponseEntity<List<Libro>> list() {
        return new ResponseEntity<>(libroService.listar(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Libro> save(@RequestBody Libro libro) {
        return new ResponseEntity<>(libroService.guardar(libro), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<Libro> update(@RequestBody Libro libro) {
        if (libroService.listarPorId(libro.getId()).isPresent()) {
            return new ResponseEntity<>(libroService.actualizar(libro), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable(required = true) Integer id) {
        return libroService.obtenerLibroPorId(id)
                .map(libro -> new ResponseEntity<>(libro, HttpStatus.OK))
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

    @GetMapping("/{id}/stock")
    public ResponseEntity<Integer> obtenerStockLibro(@PathVariable Integer id) {
        Optional<Libro> libroOptional = libroService.listarPorId(id);
        return libroOptional.map(libro -> new ResponseEntity<>(libro.getStock(), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}/decrementarStock")
    public ResponseEntity<Void> decrementarStockLibro(@PathVariable Integer id, @RequestParam Integer cantidad) {
        Optional<Libro> libroOptional = libroService.listarPorId(id);
        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            if (libro.getStock() >= cantidad) {
                libroService.decrementarStockLibro(id, cantidad);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}