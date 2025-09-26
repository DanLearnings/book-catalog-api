package com.danlearnings.bookcatalog.api.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity // 1. Diz ao JPA que esta classe é uma tabela no banco de dados
@Table(name = "books") // 2. Define o nome exato da tabela como "books"
@Getter
@Setter// 3. Lombok: Cria getters, setters, toString(), equals() e hashCode() automaticamente
@NoArgsConstructor // 4. Lombok: Cria um construtor sem argumentos (necessário para o JPA)
@AllArgsConstructor // 5. Lombok: Cria um construtor com todos os argumentos
public class Book {

    @Id // 6. Marca este campo como a chave primária (Primary Key)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 7. Configura o ID para ser gerado pelo banco (auto-incremento)
    private Long id;

    @Column(nullable = false) // 8. Garante que esta coluna não pode ser nula no banco
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(unique = true, nullable = false) // 9. Garante que o ISBN é único e não nulo
    private String isbn;

    private Integer publicationYear;
}
