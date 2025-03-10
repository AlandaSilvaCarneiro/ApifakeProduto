package com.apiproduto.FakeApiProduto.Respository;

import com.apiproduto.FakeApiProduto.Entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryProduto extends JpaRepository<Produto,Long> {
}
