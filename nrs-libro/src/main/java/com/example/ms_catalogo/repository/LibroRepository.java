package com.example.ms_catalogo.repository;

import com.example.ms_catalogo.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Integer> {
}