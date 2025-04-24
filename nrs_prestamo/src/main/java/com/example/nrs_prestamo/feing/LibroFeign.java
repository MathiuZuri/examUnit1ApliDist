package com.example.nrs_prestamo.feing;

import com.example.nrs_prestamo.dto.LibroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nrs-libro-service", path = "/libros") // El nombre del servicio de libro
public interface LibroFeign {

    @GetMapping("/{id}/stock") // Elimina "/libros" inicial
    ResponseEntity<Integer> obtenerStockLibro(@PathVariable Integer id);

    @PutMapping("/{id}/decrementarStock") // Elimina "/libros" inicial
    ResponseEntity<Void> decrementarStockLibro(@PathVariable Integer id, @RequestParam Integer cantidad);

    @GetMapping("/{id}") // Elimina "/libros" inicial
    ResponseEntity<LibroDto> obtenerLibroPorId(@PathVariable Integer id);
}