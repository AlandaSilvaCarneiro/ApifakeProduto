package com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste;

import lombok.Builder;

import java.util.Set;

@Builder
public record DtosCompra(
        Long compraCliente,
        Set<Long> listaProduto
        //Double precoCompra
) {
}
