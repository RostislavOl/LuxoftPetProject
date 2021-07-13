package com.luxoft.library.repository;

import com.luxoft.library.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<UUID, Book> {
}
