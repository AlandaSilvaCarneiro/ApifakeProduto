package com.apiproduto.FakeApiProduto.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "COMPRA_TB")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "compra_seq")
    @SequenceGenerator(name = "compra_seq", sequenceName = "compra_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonBackReference
    private Cliente compra_cliente;

    @ManyToMany(mappedBy = "lista_compra_produto",fetch =FetchType.LAZY)
    private Set<Produto> lista_produto;

    private Double preco_compra;

    private LocalDateTime horario_compra;



    @PrePersist
    public void prePersist() {
        this.horario_compra = LocalDateTime.now(); // Define a data atual antes de salvar
        this.preco_compra = this.lista_produto.stream()
                .mapToDouble(Produto::getPreco_produto)
                .sum();
    }

}
