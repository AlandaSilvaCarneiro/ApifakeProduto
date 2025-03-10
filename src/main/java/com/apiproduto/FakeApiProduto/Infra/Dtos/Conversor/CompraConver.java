package com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor;

import com.apiproduto.FakeApiProduto.Entity.Compra;
import com.apiproduto.FakeApiProduto.Entity.Produto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCompra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosCompraResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryCliente;
import com.apiproduto.FakeApiProduto.Respository.RepositoryProduto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@RequiredArgsConstructor
@Component
public class CompraConver {

    private final RepositoryProduto repositoryProduto;

    private final RepositoryCliente repositoryCliente;
    public DtosCompraResponse paraDtos(Compra compra){
        return DtosCompraResponse.builder()
                .id(compra.getId())
                .horario_compra(compra.getHorario_compra())
                .compraCliente(compra.getCompra_cliente().getNome_cliente())
                .precoCompra(compra.getPreco_compra())
                .listaProduto(compra.getLista_produto().stream()
                        .map(Produto::getNome_produto)
                        .collect(Collectors.toSet()))



                .build();

    }
    public  Compra paraEntity(DtosCompra compra){
        return Compra.builder()
                .lista_produto(compra.listaProduto()
                        .stream()
                        .map(id -> repositoryProduto.findById(id)
                                .orElseThrow(()-> new RuntimeException("Id do conversor compra(ids list<porduto> não encontrado"))

                ).collect(Collectors.toSet()))
                .compra_cliente(repositoryCliente.findById(compra.compraCliente())
                        .orElseThrow(()-> new RuntimeException("Id do conver compra(id cliente) não encontrado")))
                .build();

    }
}
