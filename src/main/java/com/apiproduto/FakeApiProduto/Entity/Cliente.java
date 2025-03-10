package com.apiproduto.FakeApiProduto.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "CLIENTE_TB")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)

 public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq")
    @SequenceGenerator(name = "cliente_seq", sequenceName = "cliente_seq", allocationSize = 1)
    private Long id;
    private String nome_cliente;

    @Column(nullable = false,unique = true)
    private String cpfCliente;

    private String telefone_cliente;
    private String email_cliente;
    private String cartao_cliente;
    private String endreco_cliente;

    @OneToMany(mappedBy = "compra_cliente", cascade = CascadeType.ALL,fetch =FetchType.LAZY )
    @JsonManagedReference
    private Set<Compra> cliente_compras;


}
