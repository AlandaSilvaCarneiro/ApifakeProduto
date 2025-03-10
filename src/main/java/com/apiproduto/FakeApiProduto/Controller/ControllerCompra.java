package com.apiproduto.FakeApiProduto.Controller;


import com.apiproduto.FakeApiProduto.Entity.Compra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Conversor.CompraConver;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCliente;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Requeste.DtosCompra;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosClienteRespose;
import com.apiproduto.FakeApiProduto.Infra.Dtos.Response.DtosCompraResponse;
import com.apiproduto.FakeApiProduto.Service.ServiceCompra;
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
    public ResponseEntity<List<DtosCompraResponse>> listaCompra() {
        return ResponseEntity.ok().body(
                serviceCompra.listaCompra()
        );
    }
    @PutMapping("update/{id}")
    public ResponseEntity<DtosCompraResponse> updtade(@PathVariable Long id, @RequestBody DtosCompra dtoscompre){

        return ResponseEntity.accepted().body(
                serviceCompra.update(dtoscompre,id)
        );

        }

    @PostMapping
    public ResponseEntity<DtosCompraResponse> salveCompra(@RequestBody DtosCompra dtosCompra){
        return ResponseEntity.ok().body(serviceCompra.salveCompra(dtosCompra));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompra(@PathVariable Long id){
        serviceCompra.deleteCompra(id);
        return ResponseEntity.accepted().body("entidadeda deletada");
    }







}
