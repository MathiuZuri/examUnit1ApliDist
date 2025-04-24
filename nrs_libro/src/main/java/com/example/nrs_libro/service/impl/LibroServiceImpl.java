package com.example.nrs_libro.service.impl;

import com.example.nrs_libro.entity.Libro;
import com.example.nrs_libro.repository.LibroRepository;
import com.example.nrs_libro.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    @Override
    public Libro guardar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Libro actualizar(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public Optional<Libro> listarPorId(Integer id) {
        return libroRepository.findById(id);
    }

    @Override
    public void eliminarPorId(Integer id) {
        libroRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void decrementarStockLibro(Integer libroId, Integer cantidad) {
        Optional<Libro> libroOptional = libroRepository.findById(libroId);
        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();
            if (libro.getStock() >= cantidad) {
                libro.setStock(libro.getStock() - cantidad);
                libroRepository.save(libro);
            } else {
                throw new RuntimeException("No hay suficiente stock para reducir del libro con ID: " + libroId);
            }
        } else {
            throw new RuntimeException("No se encontró el libro con ID: " + libroId);
        }
    }

    @Override
    public Optional<Libro> obtenerLibroPorId(Integer id) {
        return libroRepository.findById(id);
    }
}