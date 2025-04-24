package com.example.nrs_prestamo.feing;

import com.example.nrs_prestamo.dto.LibroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "nrs-libro-service") // El nombre del servicio de libro
public interface LibroFeign {

    @GetMapping("/libros/{id}/stock")
    ResponseEntity<Integer> obtenerStockLibro(@PathVariable Integer id);

    @PutMapping("/libros/{id}/decrementarStock")
    ResponseEntity<Void> decrementarStockLibro(@PathVariable Integer id, @RequestParam Integer cantidad);

    @GetMapping("/libros/{id}")
    ResponseEntity<LibroDto> obtenerLibroPorId(@PathVariable Integer id);
}