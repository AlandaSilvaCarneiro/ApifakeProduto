package com.apiproduto.FakeApiProduto;


import com.apiproduto.FakeApiProduto.Exeptions.ExceptionCpfErro;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionErroList;
import com.apiproduto.FakeApiProduto.Exeptions.ExceptionSalveErro;
import com.apiproduto.FakeApiProduto.Exeptions.ExecptionErroDeleteBy;
import com.apiproduto.FakeApiProduto.Infra.Dtos.ErroCrudDtos;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class HandlerCrudExeptions {
    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ExceptionSalveErro.class)
    public ErroCrudDtos handlerSalve(ExceptionSalveErro ex){
        return new ErroCrudDtos(
                ex.getMessage(),
                ex.getOrige()
        );

    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ExceptionErroList.class)
    public ErroCrudDtos handlerlist(ExceptionErroList ex){
        return new ErroCrudDtos(
                ex.getMessage(),
                ex.getOrige()
        );

    }


    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ExceptionCpfErro.class)
    public ErroCrudDtos handlercpf(ExceptionCpfErro ex){
        return new ErroCrudDtos(
                ex.getMessage(),
                ex.getOrige()
        );

    }



    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ResponseBody
    @ExceptionHandler(ExecptionErroDeleteBy.class)
    public ErroCrudDtos handlerByid(ExecptionErroDeleteBy ex){
        return new ErroCrudDtos(
                ex.getMessage(),
                ex.getOrige()
        );

    }









}
