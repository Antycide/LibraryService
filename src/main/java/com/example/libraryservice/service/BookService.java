package com.example.libraryservice.service;

import com.example.libraryservice.DTO.BookResponseDTO;
import com.example.libraryservice.entity.Author;
import com.example.libraryservice.entity.Book;


import com.example.libraryservice.repository.AuthorRepository;
import com.example.libraryservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;
    public final BookMapper bookMapper;

    public ResponseEntity<BookResponseDTO> saveBook(Book book)  {
        if (bookRepository.findByNameIgnoreCase(book.getName()).isPresent()) {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
        Author author = new Author();
        author.setName(book.getAuthor().getName());
        book.setAuthor(author);
        authorRepository.save(author);
        bookRepository.save(book);

        return new ResponseEntity<>(bookMapper.toBookResponseDTO(Optional.of(book)),
                HttpStatus.ACCEPTED);
    }

    public ResponseEntity<BookResponseDTO> getBook(String name) {
        if (bookRepository.findByNameIgnoreCase(name).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bookMapper.toBookResponseDTO(bookRepository.findByNameIgnoreCase(name))
                ,HttpStatus.ACCEPTED);
    }

    public ResponseEntity<BookResponseDTO> takeBook(String name) {
        Optional<Book> book = bookRepository.findByNameIgnoreCase(name);

        if (book.isPresent() && book.get().getQuantity() > 0) {
            book.get().setQuantity(book.get().getQuantity() - 1);
            bookRepository.save(book.get());
            return new ResponseEntity<>(bookMapper.toBookResponseDTO(Optional.of(bookRepository.save(book.get()))),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<BookResponseDTO> returnBook(String name) {
        Optional<Book> book = bookRepository.findByNameIgnoreCase(name);
        if (book.isPresent()) {
            book.get().setQuantity(book.get().getQuantity() + 1);
            bookRepository.save(book.get());
            return new ResponseEntity<>(bookMapper.toBookResponseDTO(book),
                    HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<BookResponseDTO>> findAll() {
        return new ResponseEntity<>(bookRepository.findAll().stream().
                map(book -> bookMapper.toBookResponseDTO(Optional.ofNullable(book))).
                toList(), HttpStatus.ACCEPTED);
    }
}
