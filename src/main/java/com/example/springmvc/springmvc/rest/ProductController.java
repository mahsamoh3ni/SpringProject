package com.example.springmvc.springmvc.rest;

import com.example.springmvc.springmvc.dto.ProductDTO;
import com.example.springmvc.springmvc.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/{id}")
    public ProductDTO getProduct(@PathVariable(name = "id") String id) throws Exception {
        return productService.getProductById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO)  {
        return productService.createProduct(productDTO);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void editProduct(@RequestBody ProductDTO productDTO, @PathVariable(name = "id") String id) throws Exception {

        productService.editProduct(productDTO, id);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) throws Exception{

        productService.deleteProduct(id);

    }

    @GetMapping(path = "/")
    public ProductDTO getProductByName(@RequestParam String name) throws Exception{

        return productService.getProductByName(name);

    }
}
