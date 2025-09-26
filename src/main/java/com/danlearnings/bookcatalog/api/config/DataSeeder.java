// File: src/main/java/com/danlearnings/bookcatalog/api/config/DataSeeder.java

package com.danlearnings.bookcatalog.api.config;

import com.danlearnings.bookcatalog.api.domain.Book;
import com.danlearnings.bookcatalog.api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component // Diz ao Spring para gerenciar esta classe
@RequiredArgsConstructor // Lombok para injeção de dependência
public class DataSeeder implements CommandLineRunner {

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) throws Exception {
        // Verifica se o banco de dados já tem algum livro para não inserir dados duplicados a cada reinicialização
        if (bookRepository.count() == 0) {
            System.out.println("No books found. Seeding database...");

            Book book1 = new Book(null, "The Lord of the Rings", "J.R.R. Tolkien", "978-0618640157", 1954);
            Book book2 = new Book(null, "1984", "George Orwell", "978-0451524935", 1949);
            Book book3 = new Book(null, "Dune", "Frank Herbert", "978-0441013593", 1965);

            bookRepository.save(book1);
            bookRepository.save(book2);
            bookRepository.save(book3);

            System.out.println("Database seeded with 3 books.");
        } else {
            System.out.println("Database already contains data. No seeding necessary.");
        }
    }
}