package com.apiproduto.FakeApiProduto.Service;


import com.apiproduto.FakeApiProduto.Entity.Fornecedor;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionErroList;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionSalveErro;
import com.apiproduto.FakeApiProduto.Exeptions.ExecptionErroDeleteBy;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.FornecedorConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosFornecedor;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosFornecedorResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryFornecedor;
import com.apiproduto.FakeApiProduto.Respository.RepositoryProduto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceFornecedor {
    private final RepositoryFornecedor repositoryFornecedor;

    private final FornecedorConver fornecedorConver;

    private final RepositoryProduto repositoryProduto;


    public List<DtosFornecedorResponse> listaFornecedor(){
        try {
            return repositoryFornecedor.findAll().stream().map(fornecedorConver::paraDtos).toList();
        } catch (RuntimeException e) {
            throw new ExceptionErroList("erro na geração da lista","Fornecedor");
        }

    }

    public DtosFornecedorResponse salvaFornecedore(DtosFornecedor dtosFornecedor){
        try {
            var salveFronecedor= repositoryFornecedor.save(fornecedorConver.paraEntity(dtosFornecedor));
            return fornecedorConver.paraDtos(salveFronecedor);
        }catch (Exception e) {
            throw new ExceptionSalveErro("Erro no salvamento","Fornecedor");
        }

    }

    public DtosFornecedorResponse upsateFornecedor(Long id,DtosFornecedor dtosFornecedor){
       Fornecedor fornecedorUpdate;
       try {
           fornecedorUpdate = repositoryFornecedor.findById(id).orElseThrow(()-> new RuntimeException("Erro na busca da referencia do id de ofrnecedor no metodo update"));
       }catch (RuntimeException IdErroBusca){
           throw  new RuntimeException("Erro  na busca do fornecedor no banco de dados, que seria feiro no metodo update");
       }
       fornecedorUpdate.setFornecedor_nome(dtosFornecedor.fornecedorNome());
       fornecedorUpdate.setFornecedor_telefone(dtosFornecedor.fornecedorTelefone());
       fornecedorUpdate.setFornecedo_endereco(dtosFornecedor.fornecedorEndereco());
       fornecedorUpdate.setFornecedor_cnpj(dtosFornecedor.fornecedorCnpj());
       fornecedorUpdate.setLista_produto(dtosFornecedor.listaProduto().stream()
               .map(IdProduto-> repositoryProduto.findById(IdProduto)
                       .orElseThrow(()-> new RuntimeException("Erro na busca dos Ids de produto no metodo update do atributo (lista de proutos) da entidade fornecedor"))).collect(Collectors.toSet()));

       repositoryFornecedor.save(fornecedorUpdate);

       return fornecedorConver.paraDtos(fornecedorUpdate);
    }

    public void deleteFornecedor(Long id){
        try {
            repositoryFornecedor.deleteById(id);
        } catch (RuntimeException e) {
            throw new ExecptionErroDeleteBy("Erro na deleção do id","Fornecedor");
        }

    }

}
