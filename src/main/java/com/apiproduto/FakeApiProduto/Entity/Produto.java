    package com.apiproduto.FakeApiProduto.Entity;


    import com.fasterxml.jackson.annotation.JsonIdentityInfo;
    import com.fasterxml.jackson.annotation.ObjectIdGenerators;
    import jakarta.persistence.*;
    import lombok.*;

    import java.util.Set;

    @Entity
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Table(name = "PRODUTO_TB")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    public class Produto {
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_seq")
        @SequenceGenerator(name = "produto_seq", sequenceName = "produto_seq", allocationSize = 1)
        private Long id;
        private  String nome_produto;
        private Double preco_produto;

        @ManyToMany
        @JoinTable(
                name = "PRODUTO_FORNECEDO_TB",
                joinColumns = @JoinColumn(name = "produto_id"),
                inverseJoinColumns = @JoinColumn(name = "fornecedo_id")
        )
        private Set<Fornecedor> lista_fornecedor;

        private  Categoria produto_categoria;
        private  Integer produto_Quantidade;
        @ManyToMany
        @JoinTable(
                name = "PRODUTOS_COMPRA_TB",
                joinColumns = @JoinColumn(name = "produto_id"),
                inverseJoinColumns = @JoinColumn(name = "compra_id")
        )

        private Set<Compra> lista_compra_produto;


    }
