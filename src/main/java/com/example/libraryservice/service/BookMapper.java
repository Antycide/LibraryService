package com.example.libraryservice.service;

import com.example.libraryservice.DTO.BookResponseDTO;
import com.example.libraryservice.entity.Book;
import com.example.libraryservice.exception.BookDoesntExistException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookMapper {


   public BookResponseDTO toBookResponseDTO(Optional<Book> book) {
        Book opBook = book.orElseThrow(() -> new BookDoesntExistException("Book doesn't exist"));
        return new BookResponseDTO(opBook.getName(), opBook.getAuthor().getName(), opBook.getQuantity());
    }
}
