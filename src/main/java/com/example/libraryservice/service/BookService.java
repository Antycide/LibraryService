package com.example.libraryservice.service;

import com.example.libraryservice.DTO.BookResponseDTO;
import com.example.libraryservice.entity.Author;
import com.example.libraryservice.entity.Book;


import com.example.libraryservice.exception.BookAlreadyTakenException;
import com.example.libraryservice.exception.BookDoesntExistException;
import com.example.libraryservice.repository.AuthorRepository;
import com.example.libraryservice.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookService {

    public final BookRepository bookRepository;
    public final AuthorRepository authorRepository;
    public final BookMapper bookMapper;

    public BookResponseDTO saveBook(Book book) throws BookDoesntExistException  {
        if (bookRepository.findByNameIgnoreCase(book.getName()).isPresent()) {
            throw new BookAlreadyTakenException("Book already exists");
        }
        Author author = new Author();
        author.setName(book.getAuthor().getName());
        book.setAuthor(author);
        authorRepository.save(author);
        bookRepository.save(book);

        return bookMapper.toBookResponseDTO(Optional.of(book));
    }

    public BookResponseDTO getBook(String name) throws BookAlreadyTakenException {
        if (bookRepository.findByNameIgnoreCase(name).isEmpty()) {
            throw new BookAlreadyTakenException("Book was already taken");
        }
        return bookMapper.toBookResponseDTO(bookRepository.findByNameIgnoreCase(name));
    }

    public BookResponseDTO takeBook(String name) throws BookAlreadyTakenException {
        Optional<Book> book = bookRepository.findByNameIgnoreCase(name);

        if (book.isPresent() && book.get().getQuantity() > 0) {
            book.get().setQuantity(book.get().getQuantity() - 1);
            bookRepository.save(book.get());
            return bookMapper.toBookResponseDTO(Optional.of(bookRepository.save(book.get())));
        }
       throw new BookAlreadyTakenException("Book already taken");
    }

    public BookResponseDTO returnBook(String name) throws BookDoesntExistException {
        Optional<Book> book = bookRepository.findByNameIgnoreCase(name);
        if (book.isEmpty()) {
            throw new BookDoesntExistException("Book does not exist");
        }
        book.get().setQuantity(book.get().getQuantity() + 1);
        bookRepository.save(book.get());
        return bookMapper.toBookResponseDTO(book);

    }

    public List<BookResponseDTO> findAll() {
        return bookRepository.findAll().stream().
                map(book -> bookMapper.toBookResponseDTO(Optional.ofNullable(book))).
                toList();
    }
}
