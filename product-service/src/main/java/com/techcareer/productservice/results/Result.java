package com.techcareer.productservice.results;

public class Result {
    boolean success;
    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	String message;

    public Result (boolean success){
        this.success=success;
    }

    public Result(String message){
        this.message=message;
    }
    
    public Result(boolean success,String message){
        this.message=message;
        this.success=success;
    }

}
