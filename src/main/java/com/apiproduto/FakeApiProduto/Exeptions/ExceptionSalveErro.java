package com.apiproduto.FakeApiProduto.Exeptions;

public class ExceptionSalveErro extends RuntimeException {
   private final String orige;
    public ExceptionSalveErro(String message,String orige) {
        super(message);
        this.orige = orige;
    }

    public String getOrige() {
        return orige;
    }
}
