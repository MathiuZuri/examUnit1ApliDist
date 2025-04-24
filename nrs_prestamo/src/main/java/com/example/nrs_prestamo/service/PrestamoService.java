package com.example.nrs_prestamo.service;

import com.example.nrs_prestamo.entity.Prestamo;
import java.util.List;
import java.util.Optional;

public interface PrestamoService {
    List<Prestamo> getAllPrestamos();
    Optional<Prestamo> getPrestamoById(Integer id);
    Prestamo savePrestamo(Prestamo prestamo);
    void deletePrestamo(Integer id);

    // Nuevos métodos para interactuar con el servicio de libros
    void procesarPrestamo(Prestamo prestamo);
    void procesarDevolucion(Integer prestamoId);

    Prestamo actualizar(Prestamo prestamo);

    String obtenerTituloLibro(Integer libroId);

    String obtenerNombreUsuario(Integer usuarioId);

    String obtenerEstadoUsuario(Integer usuarioId);

    Optional<Prestamo> obtenerPrestamoConUsuarioPorId(Integer id);

    Optional<Prestamo> obtenerPrestamoPorId(Integer id);

    Integer obtenerStockLibro(Integer libroId);

    void decrementarStockLibro(Integer libroId, Integer cantidad);

    List<Prestamo> getPrestamosByUsuarioId(Integer usuarioId);

    List<Prestamo> getPrestamosByEstado(String estado);

    List<Prestamo> getPrestamosVencidos();

    // Otros métodos
}
