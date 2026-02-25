package com.apiproduto.FakeApiProduto.Exeptions;

public class ExceptionCpfErro extends RuntimeException {

    private final String orige;

    public ExceptionCpfErro(String message, String orige ) {
        super(message);
        this.orige = orige;


        ;
    }

    public String getOrige() {
        return orige;
    }
}
