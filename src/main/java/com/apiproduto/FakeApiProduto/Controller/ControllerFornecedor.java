package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosFornecedor;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosFornecedorResponse;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosProdutoResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryFornecedor;
import com.apiproduto.FakeApiProduto.Service.ServiceFornecedor;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fornecedor")
@RequiredArgsConstructor
public class ControllerFornecedor {
    private final RepositoryFornecedor repositoryFornecedor;

    private final ServiceFornecedor serviceFornecedor;

    @Operation(description = "Endpoit responsavel por trazer uma lista de fornecedores registrado")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Lista traga com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    @GetMapping
    public ResponseEntity<List<DtosFornecedorResponse>> ListaDeFornecedore(){

        return ResponseEntity.ok().body(serviceFornecedor.listaFornecedor());
    }


    @Operation(description = "Endpoit responsavel por atualizar um fornecedor")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "Fornecedor atualizado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    @PutMapping("/update/{id}")
    public ResponseEntity<DtosFornecedorResponse> UpdateDeFornecedor(@PathVariable Long id, @RequestBody DtosFornecedor dtosFornecedor){
        return ResponseEntity.ok().body(serviceFornecedor.upsateFornecedor(id, dtosFornecedor));

    }
    @PostMapping()
    @Operation(description = "Endpoit responsavel por criar um objeto fornecedo")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "fornecedor criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<DtosFornecedorResponse> SalveFornecedor(@RequestBody DtosFornecedor dtosFornecedor){
        return ResponseEntity.ok().body(serviceFornecedor.salvaFornecedore(dtosFornecedor));

    }

    @DeleteMapping("/{id}")
    @Operation(description = "Endpoit responsavel por deletar um fornecedor")
    @ApiResponses( value = {
            @ApiResponse(responseCode = "201", description = "fornecedor deletado com sucesso com sucesso"),
            // @ApiResponse(responseCode = "422", description = "Campos não atende aos requisitos "),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno no sevidor")})

    public ResponseEntity<String> DeleteFornecedor(@PathVariable Long id){
        serviceFornecedor.deleteFornecedor(id);
        return ResponseEntity.ok().body("Fornecedor deletado com sucesso");

    }
}
