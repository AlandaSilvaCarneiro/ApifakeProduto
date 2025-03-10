package com.apiproduto.FakeApiProduto.Entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = "FORNECEDOR_TB")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_fornecedor_seq")
    @SequenceGenerator(name = "produto_fornecedor_seq", sequenceName = "produto_fornecedor_seq", allocationSize = 1)
    private  Long id;

    @Column(nullable = false,unique = true)
    private String fornecedor_cnpj;
    private String fornecedor_nome;
    private String fornecedo_endereco;
    private String fornecedor_telefone;

    @ManyToMany(mappedBy = "lista_fornecedor",fetch =FetchType.LAZY)
    private Set<Produto> lista_produto;
}
