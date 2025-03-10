package com.apiproduto.FakeApiProduto.Infra.Dtos.Response;

import lombok.Builder;

import java.util.Set;


@Builder
public record DtosFornecedorResponse(
        Long id,
        String fornecedorCnpj,
        String fornecedorNome,
        String fornecedorEndereco,
        String fornecedorTelefone,
        Set<String> listaProduto

) {
}
