package com.danlearnings.bookcatalog.api.repository;

import com.danlearnings.bookcatalog.api.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 1. Indica ao Spring que esta é uma interface de repositório
public interface BookRepository extends JpaRepository<Book, Long> {
    // 2. A mágica acontece aqui!
    // Ao estender JpaRepository, nós automaticamente ganhamos métodos como:
    // save(), findById(), findAll(), deleteById(), e muitos outros.
    // Não precisamos escrever nenhuma implementação!
}
