package com.apiproduto.FakeApiProduto.Exeptions;

public class ExceptionErroList extends RuntimeException {
    private final String orige;
    public ExceptionErroList(String message,String orige   ) {
        super(message);
        this.orige = orige;
    }
    public String getOrige() {
        return orige;
    }
}
