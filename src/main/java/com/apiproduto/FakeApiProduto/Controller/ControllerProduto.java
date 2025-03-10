package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Entity.Produto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.ProdutoConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosProduto;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosProdutoResponse;
import com.apiproduto.FakeApiProduto.Service.ServiceProduto;
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
    public ResponseEntity<List<DtosProdutoResponse>> getlistaProduto(){
        return ResponseEntity.ok().body(
                serviceProduto.listaDeProduto());
    }


    @PutMapping("update/{id}")
public ResponseEntity<DtosProdutoResponse> updateProduto(@PathVariable Long id, @RequestBody DtosProduto dtosProduto){
        return ResponseEntity.accepted().body(serviceProduto.update(id, dtosProduto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id){
        serviceProduto.deleteProduto(id);
        return ResponseEntity.ok().body("Produto deletado com sucesso");
    }

    @PostMapping()

     public ResponseEntity<DtosProdutoResponse> salveProduto(@RequestBody DtosProduto dtosProduto){
        return ResponseEntity.ok().body(serviceProduto.salveProduto(dtosProduto));
    }
}
