package com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor;

import com.apiproduto.FakeApiProduto.Entity.Fornecedor;
import com.apiproduto.FakeApiProduto.Entity.Produto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosFornecedor;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosFornecedorResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryProduto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class FornecedorConver {
    private final RepositoryProduto repositoryProduto;

    public  DtosFornecedorResponse paraDtos(Fornecedor fornecedor){

        return DtosFornecedorResponse.builder()
                .fornecedorCnpj(fornecedor.getFornecedor_cnpj())
                .fornecedorEndereco(fornecedor.getFornecedo_endereco())
                .fornecedorTelefone(fornecedor.getFornecedor_telefone())
                .listaProduto(fornecedor.getLista_produto().stream().map(Produto::getNome_produto)
                        .collect(Collectors.toSet()))
                .id(fornecedor.getId())
                .fornecedorNome(fornecedor.getFornecedor_nome())
                .build();
    }

    public Fornecedor paraEntity(DtosFornecedor fornecedor){
        return Fornecedor.builder()
                .fornecedor_cnpj(fornecedor.fornecedorCnpj())
                .fornecedor_nome(fornecedor.fornecedorNome())
                .fornecedo_endereco(fornecedor.fornecedorEndereco())
                .fornecedor_telefone(fornecedor.fornecedorTelefone())
                .lista_produto(fornecedor.listaProduto()
                        .stream()
                        .map(IdProsuto -> repositoryProduto.findById(IdProsuto)
                        .orElseThrow(()-> new RuntimeException("Ids do Convesor de Fornecedor(Lista de Ids Produtos não encontrado)")))
                        .collect(Collectors.toSet()))

                .build();
    }
}
