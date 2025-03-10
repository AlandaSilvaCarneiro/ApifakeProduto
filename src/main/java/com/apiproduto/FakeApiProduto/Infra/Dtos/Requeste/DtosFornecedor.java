package com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste;

import java.util.Set;

public record DtosFornecedor(
        String fornecedorCnpj,
        String fornecedorNome,
        String fornecedorEndereco,
        String fornecedorTelefone,
        Set<Long> listaProduto

) {
}
