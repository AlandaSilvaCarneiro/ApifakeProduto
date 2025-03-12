package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Entity.Compra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.CompraConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCompra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosClienteRespose;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosCompraResponse;
import com.apiproduto.FakeApiProduto.Service.ServiceCompra;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compra")
@RequiredArgsConstructor
public class ControllerCompra {
    private final ServiceCompra serviceCompra;

    private final CompraConver compraConver;


    @GetMapping
    @Operation(description = "Endpoit responsavel por trazer um lista de compras")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Lista buscada com sucesso"),
             @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<List<DtosCompraResponse>> listaCompra() {
        return ResponseEntity.ok().body(
                serviceCompra.listaCompra()
        );
    }
    @PutMapping("update/{id}")
    @Operation(description = "Endpoit responsavel por atualizar o objeto compra")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Compra atualizada com sucesso"),
             @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<DtosCompraResponse> updtade(@PathVariable Long id, @RequestBody DtosCompra dtoscompre){

        return ResponseEntity.accepted().body(
                serviceCompra.update(dtoscompre,id)
        );

        }

    @PostMapping
    @Operation(description = "Endpoit responsavel por registra uma compra")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Compra registrada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<DtosCompraResponse> salveCompra(@RequestBody DtosCompra dtosCompra){
        return ResponseEntity.ok().body(serviceCompra.salveCompra(dtosCompra));

    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoit responsavel por deletar uma compra ")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Compra deletado com sucesso com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<String> deleteCompra(@PathVariable Long id){
        serviceCompra.deleteCompra(id);
        return ResponseEntity.accepted().body("entidadeda deletada");
    }







}
