package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Entity.Cliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.ClienteConversor;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosClienteRespose;
import com.apiproduto.FakeApiProduto.Service.ServiceCliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(description = "Endpoit responsavel por cadastra um Cliente")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "201", description = "Cliente Cadastro com sucesso"),
        @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
        @ApiResponse(responseCode = "400", description = "Erro de requisição"),
        @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")
    })
    public ResponseEntity<DtosClienteRespose> salveCliente(@RequestBody DtosCliente dtosCliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                serviceCliente.salve(clienteConversor.paraEntity(dtosCliente)));
    }

    @PutMapping("/upate/{id}")
    @Operation(description = "Endpoit responsavel por atualizar um Cliente")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Cliente atualizado  com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})
    public ResponseEntity<DtosClienteRespose> update(@PathVariable Long id ,@RequestBody DtosCliente dtosCliente){
        return ResponseEntity.ok().body(
                serviceCliente.update(id ,dtosCliente));
    }

    @GetMapping
    @Operation(description = "Endpoit responsavel por retorna um alista de Clientes")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Lista retonada com sucesso"),
            // @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})
    public ResponseEntity<List<DtosClienteRespose>> listaCliente(){
        return ResponseEntity.accepted().body(serviceCliente.listaCliente());
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoit responsavel por deletar um Clientes")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Cliente deletado com sucesso com sucesso"),
            // @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<String> delete(@PathVariable Long id){
        serviceCliente.deltecliente(id);
        return ResponseEntity.accepted().body("cliente deletado");
    }
}
