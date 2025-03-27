package com.scaler.productservicemod.exeptions;

public class ProductNotExistsException extends Exception{
    public ProductNotExistsException(String message){
        super(message);
    }
}
