package com.apiproduto.FakeApiProduto.Service;

import com.apiproduto.FakeApiProduto.Entity.Categoria;
import com.apiproduto.FakeApiProduto.Entity.Produto;
import com.apiproduto.FakeApiProduto.Respository.RepositoryProduto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProdutoTest {

    @Mock
    private RepositoryProduto repositoryProduto;

    @InjectMocks
    private ServiceProduto serviceProduto;


    @BeforeEach
    void  setup(){
        Produto produto = new Produto(
                1L,"Notbook",
                2600.00,null,
                Categoria.ELETRONICO,4,
                null);
                repositoryProduto.save(produto);

    }

    @Test
    public void testSalvandoProduto(){
          Produto produtobusca = repositoryProduto.getReferenceById(1L);
          assertThat(produtobusca.getId()).isNotNull();

          verify(repositoryProduto,times(1)).findById(1L);
         // verify(repositoryProduto,times(1)).save(p);


    }

    



}
