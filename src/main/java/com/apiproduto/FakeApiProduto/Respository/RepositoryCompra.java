package com.apiproduto.FakeApiProduto.Respository;

import com.apiproduto.FakeApiProduto.Entity.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryCompra extends JpaRepository<Compra,Long> {
}
