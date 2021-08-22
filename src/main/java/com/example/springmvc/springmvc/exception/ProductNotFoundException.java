package com.example.springmvc.springmvc.exception;

public class ProductNotFoundException extends ProductException{

    public ProductNotFoundException (){
        super("your desired product not found");
    }


}
