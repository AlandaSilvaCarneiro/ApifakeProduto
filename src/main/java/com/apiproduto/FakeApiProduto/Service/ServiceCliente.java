package com.apiproduto.FakeApiProduto.Service;


import com.apiproduto.FakeApiProduto.Entity.Cliente;
import com.apiproduto.FakeApiProduto.Entity.Compra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.ClienteConversor;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.CompraConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosClienteRespose;
import com.apiproduto.FakeApiProduto.Respository.RepositoryCliente;
import com.apiproduto.FakeApiProduto.Respository.RepositoryCompra;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceCliente {
    private final RepositoryCliente repositoryCliente;

    private final ClienteConversor clienteConversor;

    private  final RepositoryCompra repositoryCompra;
    public DtosClienteRespose salve(Cliente cliente){
        return clienteConversor.paraDto(
                repositoryCliente.save(cliente));

    }

    public  DtosClienteRespose update(Long id,DtosCliente cliente){
        Cliente clieteupdate;
        try {
            clieteupdate = repositoryCliente.findById(id).orElseThrow(()-> new RuntimeException("cpf não encotrado"));
        }catch(Exception ExeptionCpf){
            throw  new RuntimeException("o cpf do cliente não foi encontrado");
        }

        clieteupdate.setCartao_cliente(cliente.cartaoCliente());
        clieteupdate.setCpfCliente(cliente. cpfCliente());
        clieteupdate.setEmail_cliente(cliente.emailCliente());
        clieteupdate.setNome_cliente(cliente.nomeCliente());
        clieteupdate.setEndreco_cliente(cliente.enderecoCliente());
        clieteupdate.setTelefone_cliente(cliente.telefoneCliente());
        clieteupdate.setCliente_compras(cliente.clienteCompras().stream()
                .map(IdCompra->repositoryCompra.findById(id).orElseThrow(()-> new RuntimeException("Busca de ids(compra)no  update erro")))
                .collect(Collectors.toSet()));
      return  clienteConversor.paraDto(repositoryCliente.save(clieteupdate));

    }

    public List<DtosClienteRespose> listaCliente(){

        return repositoryCliente.findAll().
                stream().
                map(Cliente -> clienteConversor.paraDto(Cliente)).toList();

    }

    public  void deltecliente(Long id){
        repositoryCliente.deleteById(id);

    }
}
