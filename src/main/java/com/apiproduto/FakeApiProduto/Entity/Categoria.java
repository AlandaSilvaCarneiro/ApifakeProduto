package com.apiproduto.FakeApiProduto.Entity;

public enum Categoria {

    ELETRONICO(1L),
    ROUPA(2L),
    ALIMENTO(3L),
    BEBIDAS(4L);


    private final Long id;

    Categoria(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public static Categoria fromId(Long id) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.getId().equals(id)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria inválida: " + id);
    }

}
