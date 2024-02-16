package com.example.libraryservice.controller;

import com.example.libraryservice.DTO.BookResponseDTO;

import com.example.libraryservice.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/quantity")
public class LibraryController {

    private final BookService bookService;

    @GetMapping("/taking/books/{name}")
    public ResponseEntity<BookResponseDTO> takeBook(@PathVariable String name)  {
        return bookService.takeBook(name);
    }

    @PostMapping("/return/books/{name}")
    public ResponseEntity<BookResponseDTO> returnBook(@PathVariable String name) {
        return bookService.returnBook(name);
    }

}
