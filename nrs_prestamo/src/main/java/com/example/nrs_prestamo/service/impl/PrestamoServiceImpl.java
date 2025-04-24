package com.example.nrs_prestamo.service.impl;

import com.example.nrs_prestamo.entity.Prestamo;
import com.example.nrs_prestamo.feing.LibroFeign;
import com.example.nrs_prestamo.feing.UsuarioFeign;
import com.example.nrs_prestamo.repository.PrestamoRepository;
import com.example.nrs_prestamo.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroFeign libroFeign;

    @Autowired
    private UsuarioFeign usuarioFeign;

    @Override
    public List<Prestamo> listar() {
        return prestamoRepository.findAll();
    }

    @Override
    public Optional<Prestamo> listarPorId(Integer id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Prestamo guardar(Prestamo prestamo) {

        Prestamo prestamoGuardado = prestamoRepository.save(prestamo);

        // Verificación de disponibilidad de libros y estado del usuario antes de guardar
        if (!verificarDisponibilidadLibros(prestamo.getLibrosPrestados().keySet())) {
            throw new RuntimeException("Uno o más libros no están disponibles.");
        }
        if (!verificarUsuarioActivo(prestamo.getUsuarioId())) {
            throw new RuntimeException("El usuario no está activo.");
        }
        if (obtenerCantidadPrestamosActivos(prestamo.getUsuarioId()) >= 5) {
            throw new RuntimeException("El usuario ha alcanzado el límite de préstamos activos.");
        }
        for (Integer libroId : prestamo.getLibrosPrestados().keySet()) {
            decrementarStockLibros(libroId, 1); // Llama al método para cada libro
        }

        prestamo.setFechaPrestamo(LocalDate.now());
        prestamo.setEstado("activo");

        return prestamoGuardado;
    }

    @Override
    public Prestamo actualizar(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPorId(Integer id) {
        prestamoRepository.deleteById(id);
    }

    private boolean verificarDisponibilidadLibros(java.util.Set<Integer> libroIds) {
        for (Integer libroId : libroIds) {
            try {
                Integer stock = libroFeign.obtenerStockLibro(libroId).getBody();
                if (stock == null || stock <= 0) {
                    return false;
                }
            } catch (Exception e) {
                // Manejar error si el servicio de libro no responde o no encuentra el libro
                System.err.println("Error al verificar disponibilidad del libro con ID " + libroId + ": " + e.getMessage());
                return false; // Considerar el libro como no disponible en caso de error
            }
        }
        return true;
    }

    private boolean verificarUsuarioActivo(Integer usuarioId) {
        try {
            String estado = usuarioFeign.obtenerEstadoUsuario(usuarioId).getBody();
            return estado != null && estado.equalsIgnoreCase("activo");
        } catch (Exception e) {
            // Manejar error si el servicio de usuario no responde o no encuentra el usuario
            System.err.println("Error al verificar estado del usuario con ID " + usuarioId + ": " + e.getMessage());
            return false; // Considerar el usuario como inactivo en caso de error
        }
    }

    private int obtenerCantidadPrestamosActivos(Integer usuarioId) {
        return prestamoRepository.countByUsuarioIdAndEstado(usuarioId, "activo");
    }

    @Transactional // Ahora es un método público o protegido
    @Override
    public void decrementarStockLibros(Integer libroId, Integer cantidad) {
        try {
            libroFeign.decrementarStockLibro(libroId, cantidad);
        } catch (Exception e) {
            System.err.println("Error al decrementar stock del libro con ID " + libroId + ": " + e.getMessage());
            throw new RuntimeException("Error al procesar el préstamo debido a un problema con el stock de los libros.");
        }
    }
}