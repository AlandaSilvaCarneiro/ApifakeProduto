package com.apiproduto.FakeApiProduto.Infra.Dtos.Response;

import lombok.Builder;

import java.util.Set;


@Builder
public record DtosProdutoResponse(
        Long id,
        String nomeProduto,
        Double precoProduto,
        Set<String> listaFornecedor,
        String produtoCategoria,
        Integer produtoQuantidade,
        Set<Long> listaCompraProduto

) {
}
