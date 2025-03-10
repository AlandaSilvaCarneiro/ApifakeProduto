package com.apiproduto.FakeApiProduto.Respository;

import com.apiproduto.FakeApiProduto.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositoryCliente extends JpaRepository<Cliente,Long> {
    Optional<Cliente> findByCpfCliente(String cpfCliente);
}
