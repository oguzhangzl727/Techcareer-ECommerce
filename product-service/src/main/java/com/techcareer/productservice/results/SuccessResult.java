package com.techcareer.productservice.results;


public class SuccessResult extends Result{

    public SuccessResult(){
        super(true);
    }

    public SuccessResult(String message){
        super(true,message);
    }
}
