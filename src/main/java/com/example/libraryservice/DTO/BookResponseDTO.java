package com.example.libraryservice.DTO;

import com.example.libraryservice.entity.Author;

public record BookResponseDTO(String name,
                              String author,
                              Integer quantity) {

}
