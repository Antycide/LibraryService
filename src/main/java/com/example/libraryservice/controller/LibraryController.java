package com.example.libraryservice.controller;

import com.example.libraryservice.DTO.BookResponseDTO;

import com.example.libraryservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/quantity")
public class LibraryController {

    private final BookService bookService;

    @PostMapping("/taking/books/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDTO takeBook(@PathVariable String name)  {
        return bookService.takeBook(name);
    }

    @PostMapping("/return/books/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO returnBook(@PathVariable String name) {
        return bookService.returnBook(name);
    }

}
