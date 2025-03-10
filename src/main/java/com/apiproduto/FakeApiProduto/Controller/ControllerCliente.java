package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Entity.Cliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.ClienteConversor;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosClienteRespose;
import com.apiproduto.FakeApiProduto.Service.ServiceCliente;
import jakarta.persistence.PostUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cliente")
@RequiredArgsConstructor
public class ControllerCliente {
    private final ServiceCliente serviceCliente;

    private final ClienteConversor clienteConversor;

    @PostMapping
    public ResponseEntity<DtosClienteRespose> salveCliente(@RequestBody DtosCliente dtosCliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                serviceCliente.salve(clienteConversor.paraEntity(dtosCliente)));
    }
    @PutMapping("/upate/{id}")
    public ResponseEntity<DtosClienteRespose> update(@PathVariable Long id ,@RequestBody DtosCliente dtosCliente){
        return ResponseEntity.ok().body(
                serviceCliente.update(id ,dtosCliente));
    }

    @GetMapping
    public ResponseEntity<List<DtosClienteRespose>> listaCliente(){
        return ResponseEntity.accepted().body(serviceCliente.listaCliente());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        serviceCliente.deltecliente(id);
        return ResponseEntity.accepted().body("cliente deletado");
    }
}
