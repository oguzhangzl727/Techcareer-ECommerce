package com.techcareer.productservice.results;


import lombok.Getter;

@Getter
public class DataResult<T> extends Result {
    T data;

    public DataResult(boolean success, T data){
        super(success);
        this.data=data;
    }

    public DataResult(boolean success, String message, T data){
        super(success, message);
        this.data=data;
    }
}
