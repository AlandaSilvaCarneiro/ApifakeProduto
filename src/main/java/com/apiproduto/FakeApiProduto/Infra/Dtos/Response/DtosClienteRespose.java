package com.apiproduto.FakeApiProduto.Infra.Dtos.Response;

import lombok.Builder;

import java.util.Set;


@Builder
public record DtosClienteRespose(
        Long id,
        String nomeCliente,
        String cpfCliente,
        String telefoneCliente,
        String emailCliente,
        String cartaoCliente,
        String enderecoCliente,
        Set<Long> clienteCompras
) {
}
