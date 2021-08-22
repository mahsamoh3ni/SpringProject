package com.example.springmvc.springmvc.exceptionHandler;

import com.example.springmvc.springmvc.dto.MessageDTO;
import com.example.springmvc.springmvc.exception.ProductException;
import com.example.springmvc.springmvc.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class ProductHandler {

    @ExceptionHandler(value = ProductException.class)
    public ResponseEntity<Object> processProductNotFound(ProductNotFoundException productException) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage("Product not found!");
        messageDTO.setType("ERROR");
        return new ResponseEntity<>(messageDTO, HttpStatus.NOT_FOUND);
    }
}
