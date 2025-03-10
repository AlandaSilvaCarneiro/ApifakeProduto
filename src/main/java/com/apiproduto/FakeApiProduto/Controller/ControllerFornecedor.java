package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosFornecedor;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosFornecedorResponse;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosProdutoResponse;
import com.apiproduto.FakeApiProduto.Respository.RepositoryFornecedor;
import com.apiproduto.FakeApiProduto.Service.ServiceFornecedor;
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

    @GetMapping
    public ResponseEntity<List<DtosFornecedorResponse>> ListaDeFornecedore(){

        return ResponseEntity.ok().body(serviceFornecedor.listaFornecedor());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DtosFornecedorResponse> UpdateDeFornecedor(@PathVariable Long id, @RequestBody DtosFornecedor dtosFornecedor){
        return ResponseEntity.ok().body(serviceFornecedor.upsateFornecedor(id, dtosFornecedor));

    }
    @PostMapping()
    public ResponseEntity<DtosFornecedorResponse> SalveFornecedor(@RequestBody DtosFornecedor dtosFornecedor){
        return ResponseEntity.ok().body(serviceFornecedor.salvaFornecedore(dtosFornecedor));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteFornecedor(@PathVariable Long id){
        serviceFornecedor.deleteFornecedor(id);
        return ResponseEntity.ok().body("Fornecedor deletado com sucesso");

    }
}
