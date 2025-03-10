package com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste;

import lombok.Builder;

import java.util.Set;

@Builder
public record DtosCliente(
        String nomeCliente,
        String cpfCliente,
        String telefoneCliente,
        String emailCliente,
        String cartaoCliente,
        String enderecoCliente,
        Set<Long> clienteCompras
) {
}
