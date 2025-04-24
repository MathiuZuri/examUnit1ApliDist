package com.example.nrs_libro.service; // Asegúrate de que la ruta del paquete sea correcta

import com.example.nrs_libro.entity.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> listar();
    Libro guardar(Libro libro);
    Libro actualizar(Libro libro);
    Optional<Libro> listarPorId(Integer id);
    void eliminarPorId(Integer id);
    void decrementarStockLibro(Integer id, Integer cantidad);
    Optional<Libro> obtenerLibroPorId(Integer id); // Cambiado a Optional<Libro>
}