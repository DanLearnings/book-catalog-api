// File: src/main/java/com/danlearnings/bookcatalog/api/service/BookServiceImpl.java

package com.danlearnings.bookcatalog.api.service;

import com.danlearnings.bookcatalog.api.domain.Book;
import com.danlearnings.bookcatalog.api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 1. Diz ao Spring: "Esta classe é um serviço, gerencie-a para mim!"
@RequiredArgsConstructor // 2. Lombok: Cria um construtor com os campos 'final' (para injeção de dependência)
public class BookServiceImpl implements BookService {

    // 3. Injeção de Dependência via construtor (a melhor prática)
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book saveBook(Book book) {
        // Para garantir que estamos criando um novo livro, o ID deve ser nulo
        if (book.getId() != null) {
            // Poderíamos lançar uma exceção aqui, mas por simplicidade vamos apenas definir como nulo
            book.setId(null);
        }
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(Long id, Book bookDetails) {
        // Primeiro, procuramos se o livro a ser atualizado realmente existe
        return bookRepository.findById(id)
                .map(existingBook -> { // 'map' executa o código se o livro for encontrado
                    existingBook.setTitle(bookDetails.getTitle());
                    existingBook.setAuthor(bookDetails.getAuthor());
                    existingBook.setIsbn(bookDetails.getIsbn());
                    existingBook.setPublicationYear(bookDetails.getPublicationYear());
                    // O método save() do JPA atualiza o registro se ele já tiver um ID
                    return bookRepository.save(existingBook);
                }); // Se o findById não encontrar nada, ele retorna um Optional.empty()
    }

    @Override
    public void deleteBookById(Long id) {
        // Verifica se o livro existe antes de tentar deletar para evitar erros
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        }
        // Se não existir, a operação simplesmente não faz nada.
        // Em um cenário real, poderíamos lançar uma exceção "NotFound".
    }
}