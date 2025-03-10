package com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste;

import java.util.Set;

public record DtosProduto(
        String nomeProduto,
        Double precoProduto,
        Set<Long> listaFornecedor,
        Long produtoCategoria,
        Integer produtoQuantidade,
        Set<Long> listaCompraProduto
) {
}
