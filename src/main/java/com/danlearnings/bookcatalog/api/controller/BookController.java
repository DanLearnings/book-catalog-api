// File: src/main/java/com/danlearnings/bookcatalog/api/controller/BookController.java

package com.danlearnings.bookcatalog.api.controller;

import com.danlearnings.bookcatalog.api.domain.Book;
import com.danlearnings.bookcatalog.api.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // 1. Combina @Controller e @ResponseBody, simplificando a criação de APIs REST
@RequestMapping("/api/v1/books") // 2. Define o prefixo da URL para todos os endpoints nesta classe
@RequiredArgsConstructor // 3. Lombok para injeção de dependência do serviço
public class BookController {

    private final BookService bookService;

    // GET /api/v1/books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.findAllBooks();
        return ResponseEntity.ok(books); // Retorna 200 OK com a lista de livros
    }

    // GET /api/v1/books/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.findBookById(id)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com o livro
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }

    // POST /api/v1/books
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book savedBook = bookService.saveBook(book);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED); // Retorna 201 Created com o livro salvo
    }

    // PUT /api/v1/books/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookService.updateBook(id, bookDetails)
                .map(ResponseEntity::ok) // Se atualizar, retorna 200 OK com o livro atualizado
                .orElse(ResponseEntity.notFound().build()); // Se não encontrar, retorna 404 Not Found
    }

    // DELETE /api/v1/books/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build(); // Retorna 204 No Content, indicando sucesso sem corpo de resposta
    }
}