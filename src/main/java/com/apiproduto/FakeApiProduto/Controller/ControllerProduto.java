package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Entity.Produto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.ProdutoConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosProduto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosProdutoResponse;
import com.apiproduto.FakeApiProduto.Service.ServiceProduto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ControllerProduto {

    private final ServiceProduto serviceProduto;
    private final ProdutoConver produtoConver;

    @GetMapping
    @Operation(description = "Endpoit responsavel por retorna uma lista de Produtos")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Lista de produto retornada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<List<DtosProdutoResponse>> getlistaProduto(){
        return ResponseEntity.ok().body(
                serviceProduto.listaDeProduto());
    }


    @PutMapping("update/{id}")

    @Operation(description = "Endpoit responsavel por atualizar o objeto Produto")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Produto atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<DtosProdutoResponse> updateProduto(@PathVariable Long id, @RequestBody DtosProduto dtosProduto){
        return ResponseEntity.accepted().body(serviceProduto.update(id, dtosProduto));
    }

    @DeleteMapping("/{id}")

    @Operation(description = "Endpoit responsavel por deletar um Produto")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Produto deletado  com sucesso"),
          //  @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<String> deleteProduto(@PathVariable Long id){
        serviceProduto.deleteProduto(id);
        return ResponseEntity.ok().body("Produto deletado com sucesso");
    }

    @PostMapping()

    @Operation(description = "Endpoit responsavel por cria um objeto ")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<DtosProdutoResponse> salveProduto(@RequestBody DtosProduto dtosProduto){
        return ResponseEntity.ok().body(serviceProduto.salveProduto(dtosProduto));
    }
}
