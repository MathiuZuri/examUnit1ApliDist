package com.example.ms_catalogo.service; // Asegúrate de que la ruta del paquete sea correcta

import com.example.ms_catalogo.entity.Libro;

import java.util.List;
import java.util.Optional;

public interface LibroService {

    List<Libro> listar();

    Libro guardar(Libro libro);

    Libro actualizar(Libro libro);

    Optional<Libro> listarPorId(Integer id);

    void eliminarPorId(Integer id);

    // Reemplazamos el método opcional 'decrementarStock' con 'reducirStock'
    void reducirStock(Integer libroId, Integer cantidad);


}