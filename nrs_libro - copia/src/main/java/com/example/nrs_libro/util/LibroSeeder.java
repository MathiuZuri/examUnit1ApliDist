package com.example.nrs_libro.util;

import com.example.nrs_libro.entity.Libro;
import com.example.nrs_libro.repository.LibroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class LibroSeeder implements CommandLineRunner {

    private final LibroRepository libroRepository;

    public LibroSeeder(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    @Override
    public void run(String... args) {
        // Verificamos si ya existen libros para no duplicar
        if (libroRepository.count() == 0) {
            Libro libro1 = new Libro(null, "Cien años de soledad", "978-0307264888", "Gabriel García Márquez", "Vintage", 10, "Novela");
            Libro libro2 = new Libro(null, "Orgullo y prejuicio", "978-0141439518", "Jane Austen", "Penguin Classics", 5, "Romance");
            Libro libro3 = new Libro(null, "1984", "978-0451524935", "George Orwell", "Signet Classics", 12, "Ciencia Ficción");
            Libro libro4 = new Libro(null, "El Señor de los Anillos", "978-0618260274", "J.R.R. Tolkien", "Houghton Mifflin Harcourt", 7, "Fantasía");
            Libro libro5 = new Libro(null, "Harry Potter y la piedra filosofal", "978-0590353403", "J.K. Rowling", "Scholastic", 20, "Fantasía");
            Libro libro6 = new Libro(null, "Don Quijote de la Mancha", "978-8424914217", "Miguel de Cervantes", "Austral", 3, "Clásico");
            Libro libro7 = new Libro(null, "Matar un ruiseñor", "978-0061120084", "Harper Lee", "Grand Central Publishing", 9, "Ficción Literaria");
            Libro libro8 = new Libro(null, "El Gran Gatsby", "978-0743273565", "F. Scott Fitzgerald", "Scribner", 15, "Clásico");
            Libro libro9 = new Libro(null, "Los juegos del hambre", "978-0439023481", "Suzanne Collins", "Scholastic Press", 18, "Ciencia Ficción");
            Libro libro10 = new Libro(null, "Steve Jobs", "978-1451648539", "Walter Isaacson", "Simon & Schuster", 6, "Biografía");

            libroRepository.save(libro1);
            libroRepository.save(libro2);
            libroRepository.save(libro3);
            libroRepository.save(libro4);
            libroRepository.save(libro5);
            libroRepository.save(libro6);
            libroRepository.save(libro7);
            libroRepository.save(libro8);
            libroRepository.save(libro9);
            libroRepository.save(libro10);

            System.out.println("Datos de libros insertados correctamente.");
        } else {
            System.out.println("Los libros ya existen, no se insertaron datos.");
        }
    }
}