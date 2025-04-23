package com.example.ms_catalogo.controller;

import com.example.ms_catalogo.entity.Categoria;
import com.example.ms_catalogo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private LibroService libroService;

    @GetMapping()
    public ResponseEntity<List<Categoria>> list() {
        return new ResponseEntity<>(libroService.listar(), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
        return new ResponseEntity<>(libroService.guardar(categoria), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<Categoria> update(@RequestBody Categoria categoria){
        if(libroService.listarPorId(categoria.getId()).isPresent()){
            return new ResponseEntity<>(libroService.actualizar(categoria), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> listById(@PathVariable(required = true) Integer id){
        return libroService.listarPorId(id)
                .map(categoria -> new ResponseEntity<>(categoria, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(required = true) Integer id){
        if(libroService.listarPorId(id).isPresent()){
            libroService.eliminarPorId(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}