package com.example.libraryservice.controller;

import com.example.libraryservice.DTO.BookResponseDTO;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@AllArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    public final BookService bookService;


    @PostMapping
    public ResponseEntity<BookResponseDTO> addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{name}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable String name){
        return bookService.getBook(name);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> findAll() {
        return bookService.findAll();
    }

}
