package com.example.nrs_libro.repository;

import com.example.nrs_libro.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
}