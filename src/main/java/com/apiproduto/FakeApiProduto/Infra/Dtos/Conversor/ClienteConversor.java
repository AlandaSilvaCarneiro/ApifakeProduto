package com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor;

import com.apiproduto.FakeApiProduto.Entity.Cliente;
import com.apiproduto.FakeApiProduto.Entity.Compra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosClienteRespose;

import com.apiproduto.FakeApiProduto.Respository.RepositoryCompra;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.parser.Entity;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClienteConversor {
    private  final RepositoryCompra repositoryCompra;
    public DtosClienteRespose paraDto(Cliente cliente) {
        return DtosClienteRespose.builder()
                .id(cliente.getId())
                .nomeCliente(cliente.getNome_cliente())
                .cpfCliente(cliente.getCpfCliente())
                .telefoneCliente(cliente.getTelefone_cliente())
                .emailCliente(cliente.getEmail_cliente())
                .cartaoCliente(cliente.getCartao_cliente())
                .enderecoCliente(cliente.getEndreco_cliente())
                .clienteCompras(cliente.getCliente_compras().stream()
                        .map(Compra::getId)
                        .collect(Collectors.toSet())
                ).build();
    }

    public Cliente paraEntity(DtosCliente dtosCliente) {
        return Cliente.builder()
                .telefone_cliente(dtosCliente.telefoneCliente())
                .cartao_cliente(dtosCliente.cartaoCliente())
                .email_cliente(dtosCliente.emailCliente())
                .nome_cliente(dtosCliente.nomeCliente())
                .endreco_cliente(dtosCliente.enderecoCliente())
                .cpfCliente(dtosCliente.cpfCliente())
                .cliente_compras(dtosCliente.clienteCompras().stream()
                        .map(id->repositoryCompra.findById(id).orElse(null))
                        .collect(Collectors.toSet()))

                .build();
    }



}
