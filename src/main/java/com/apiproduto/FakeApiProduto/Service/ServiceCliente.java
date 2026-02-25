package com.apiproduto.FakeApiProduto.Service;


import com.apiproduto.FakeApiProduto.Entity.Cliente;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionErroList;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionSalveErro;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionCpfErro;
import com.apiproduto.FakeApiProduto.Exeptions.ExecptionErroDeleteBy;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.ClienteConversor;
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
    try {
        repositoryCliente.save(cliente);
        return clienteConversor.paraDto(cliente);
    }catch (RuntimeException e){
        throw new ExceptionSalveErro("erro ao salva ", "cliente");
    }


    }

    public  DtosClienteRespose update(Long id,DtosCliente cliente){
        Cliente clieteupdate;
        try {
            clieteupdate = repositoryCliente.findById(id).orElseThrow(()-> new RuntimeException("cpf não encotrado"));
        }catch(RuntimeException e ){
            throw  new ExceptionCpfErro("o cpf do cliente não foi encontrado","cliente");
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

        try {
            return repositoryCliente.findAll().
                    stream().
                    map(clienteConversor::paraDto).toList();
        } catch (RuntimeException e) {
            throw new ExceptionErroList("Erro na busca da lista", "cliente");
        }
    }

    public  void deltecliente(Long id){

        if (repositoryCliente.existsById(id)){
            repositoryCliente.deleteById(id);

        }else{
            throw new ExecptionErroDeleteBy("erro na deleção por id", "cliente");
        }
        }


    }

