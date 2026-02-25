package com.apiproduto.FakeApiProduto.Exeptions;

public class ExecptionErroDeleteBy extends RuntimeException {
    public final  String orige;
    public ExecptionErroDeleteBy(String message, String orige) {
        super(message);
        this.orige =orige;

    }

    public String getOrige() {
        return orige;
    }
}
