package com.example.libraryservice.repository;

import com.example.libraryservice.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByNameIgnoreCase(String name);


}
