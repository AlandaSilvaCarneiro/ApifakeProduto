package com.apiproduto.FakeApiProduto.Service;

import com.apiproduto.FakeApiProduto.Entity.Categoria;
import com.apiproduto.FakeApiProduto.Entity.Produto;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionErroList;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionSalveErro;
import com.apiproduto.FakeApiProduto.Exeptions.ExecptionErroDeleteBy;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.ProdutoConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosProduto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosProdutoResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryCompra;
import com.apiproduto.FakeApiProduto.Respository.RepositoryFornecedor;
import com.apiproduto.FakeApiProduto.Respository.RepositoryProduto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProduto {
    private final RepositoryProduto respositoryProduto;

    private final ProdutoConver produtoConver;

    private final RepositoryFornecedor repositoryFornecedor;

    private final RepositoryCompra repositoryCompra;

    public DtosProdutoResponse salveProduto(DtosProduto dtosProduto){
        try {
           var salveProd = respositoryProduto.save(produtoConver.paraEntity(dtosProduto));
           return produtoConver.paraDtos(salveProd);
        } catch (RuntimeException e) {
            throw new ExceptionSalveErro("Erro no salvamento ", "Produto");
        }



    }

    public List<DtosProdutoResponse> listaDeProduto(){
        try {
            return  respositoryProduto.findAll().stream().map(produtoConver::paraDtos).toList();
        } catch (RuntimeException e) {
            throw new ExceptionErroList("Erro na geração da lista", "Produto");
        }

    }

    public DtosProdutoResponse update(Long id , DtosProduto dtosProduto){
        Produto produto;
        try {
            produto = respositoryProduto.findById(id).orElseThrow(() -> new RuntimeException("Id no update de Produto(id do produto) não encontrado"));
        }catch (RuntimeException IdErro){
            throw new RuntimeException("Erro na busca do id para pegar a referencia de um produto no banco(no metodo uodate de produto)");
        }
        produto.setNome_produto(dtosProduto.nomeProduto());
        produto.setPreco_produto(dtosProduto.precoProduto());
        produto.setProduto_categoria(Categoria.fromId(dtosProduto.produtoCategoria()));
        produto.setProduto_Quantidade(dtosProduto.produtoQuantidade());
        produto.setLista_compra_produto(dtosProduto.listaCompraProduto().stream().map(Idscompra -> repositoryCompra.findById(Idscompra).orElseThrow(() -> new RuntimeException("Erro na busca dos ids(de compras) relacionaods a lista de prondutos, no setLista<Comora> no metodo update"))).collect(Collectors.toSet()));
        produto.setLista_fornecedor(dtosProduto.listaFornecedor().stream().map(IdsFornecedores -> repositoryFornecedor.findById(IdsFornecedores).orElseThrow(()-> new RuntimeException("Erro na busca dos ids(de fornecedores) relacionaods a lista de fornecedore, no setLista<Fornecedore> no metodo update"))).collect(Collectors.toSet()));
        respositoryProduto.save(produto);
        return produtoConver.paraDtos(produto);
    }

    public void deleteProduto(Long id){
        try {
            respositoryProduto.deleteById(id);
        } catch (RuntimeException e) {
            throw new ExecptionErroDeleteBy("Erro na deleção do ID","Fornecedor");
        }


    }
}
