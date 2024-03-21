package com.example.libraryservice.controller;

import com.example.libraryservice.DTO.BookResponseDTO;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookController {

    public final BookService bookService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDTO addBook(@RequestBody Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDTO getBook(@PathVariable String name){
        return bookService.getBook(name);
    }

    @GetMapping
    public List<BookResponseDTO> findAll() {
        return bookService.findAll();
    }

}
