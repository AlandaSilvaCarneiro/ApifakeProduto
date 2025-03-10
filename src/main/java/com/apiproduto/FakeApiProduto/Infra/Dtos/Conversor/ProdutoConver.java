package com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor;

import com.apiproduto.FakeApiProduto.Entity.Categoria;
import com.apiproduto.FakeApiProduto.Entity.Compra;
import com.apiproduto.FakeApiProduto.Entity.Fornecedor;
import com.apiproduto.FakeApiProduto.Entity.Produto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCompra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosProduto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosProdutoResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryCompra;
import com.apiproduto.FakeApiProduto.Respository.RepositoryFornecedor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProdutoConver {
    public  final RepositoryCompra repositoryCompra;

    public final RepositoryFornecedor repositoryFornecedor;

    public DtosProdutoResponse paraDtos(Produto produto){
        return DtosProdutoResponse.builder()
                .nomeProduto(produto.getNome_produto())
                .precoProduto(produto.getPreco_produto())
                .id(produto.getId())
                .produtoQuantidade(produto.getProduto_Quantidade())
                .listaFornecedor(produto.getLista_fornecedor().stream().map(Fornecedor::getFornecedor_nome).collect(Collectors.toSet()))
                .produtoCategoria(produto.getProduto_categoria().name())
                .produtoQuantidade(produto.getProduto_Quantidade())
                .listaCompraProduto(produto.getLista_compra_produto().stream().map(Compra::getId)
                        .collect(Collectors.toSet()))
                .build();
    }

    public Produto paraEntity(DtosProduto produto){
        return Produto.builder()
                .nome_produto(produto.nomeProduto())
                .preco_produto(produto.precoProduto())
                .produto_categoria(Categoria.fromId(produto.produtoCategoria()))
                .produto_Quantidade(produto.produtoQuantidade())
                .lista_compra_produto(
                        produto.listaCompraProduto()
                                .stream()
                                    .map(IdCompra -> repositoryCompra.findById(IdCompra)
                                        .orElseThrow(()-> new RuntimeException("Ids do conversor de Produto entidade(lista de Ids compra não encontrado)")))
                                            .collect(Collectors.toSet()))
                .lista_fornecedor(produto.listaFornecedor()
                                    .stream()
                                        .map(IdForneceor -> repositoryFornecedor.findById(IdForneceor)
                                                .orElseThrow(()->new RuntimeException("Ids do conversor de Produto(Ids List de fornecedores  não encontrado)")))
                                                    .collect(Collectors.toSet()))
                .build();
    }
}
