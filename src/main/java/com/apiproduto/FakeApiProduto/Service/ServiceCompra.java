package com.apiproduto.FakeApiProduto.Service;

import com.apiproduto.FakeApiProduto.Entity.Cliente;
import com.apiproduto.FakeApiProduto.Entity.Compra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.CompraConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCompra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosClienteRespose;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosCompraResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryCliente;
import com.apiproduto.FakeApiProduto.Respository.RepositoryCompra;
import com.apiproduto.FakeApiProduto.Respository.RepositoryProduto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceCompra {
    private final RepositoryCompra repositoryCompra;

    private final RepositoryCliente repositoryCliente;


    private final RepositoryProduto repositoryProduto;

    private final CompraConver compraConver;


    public List<DtosCompraResponse> listaCompra(){
        return  repositoryCompra.findAll().stream().map(compraConver::paraDtos).toList();
    }


    public DtosCompraResponse salveCompra(DtosCompra dtosCompra){

        return compraConver.paraDtos(repositoryCompra.save(
                compraConver.paraEntity(dtosCompra)));
    }

    public DtosCompraResponse update(DtosCompra compra, Long id){
        Compra updateCompra;
        try {
            updateCompra = repositoryCompra.getReferenceById(id);
        }catch (Exception IdErro){
           throw  new RuntimeException("Id do update de compra não encontrado") ;
        }
           // updateCompra.setPreco_compra(compra.precoCompra());
            updateCompra.setCompra_cliente(repositoryCliente.findById(compra.compraCliente()).orElseThrow(() -> new RuntimeException("id do cliente no update de compra não encontrado")));
            updateCompra.setLista_produto(compra.listaProduto()
                    .stream()
                        .map(IdsPro -> repositoryProduto.findById(IdsPro)
                                .orElseThrow(()-> new RuntimeException("Erros na buasca dos ids de prondtudo no update de compra")))
                                    .collect(Collectors.toSet()));;


        return compraConver.paraDtos(repositoryCompra.save(updateCompra));
    }

    public void deleteCompra(Long idCompra){
        repositoryCompra.deleteById(idCompra);
    }




}
