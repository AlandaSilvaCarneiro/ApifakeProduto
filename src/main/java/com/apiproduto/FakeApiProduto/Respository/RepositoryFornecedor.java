package com.apiproduto.FakeApiProduto.Respository;

import com.apiproduto.FakeApiProduto.Entity.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFornecedor extends JpaRepository<Fornecedor,Long> {
}
