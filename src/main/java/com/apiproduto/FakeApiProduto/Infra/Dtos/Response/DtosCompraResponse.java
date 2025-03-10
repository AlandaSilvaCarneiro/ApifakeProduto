package com.apiproduto.FakeApiProduto.Infra.Dtos.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;


@Builder
public record DtosCompraResponse(
        Long id,
        String compraCliente,
        Set<String> listaProduto,
        Double precoCompra,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime horario_compra
) {
}
