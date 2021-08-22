package com.example.springmvc.springmvc.service;

import com.example.springmvc.springmvc.dto.ProductDTO;
import com.example.springmvc.springmvc.exception.ProductNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Test
    void create_should_be_successfully() {
        ProductDTO productDTO = generateProduct();

        org.assertj.core.api.Assertions.assertThat(productDTO.getId()).isNull();
        ProductDTO newProductDto = productService.createProduct(productDTO);
        org.assertj.core.api.Assertions.assertThat(productDTO.getName()).isEqualTo(newProductDto.getName());
        org.assertj.core.api.Assertions.assertThat(productDTO.getDescription()).isEqualTo(newProductDto.getDescription());
        org.assertj.core.api.Assertions.assertThat(newProductDto.getId()).isNotNull();

    }

    @Test
    void edit_and_get_should_be_successfully() throws Exception {
        ProductDTO productDTO = generateProduct();
        ProductDTO createProduct = productService.createProduct(productDTO);

        ProductDTO editProduct = new ProductDTO(null,
                "editProduct",
                "editProduct",
                "category",
                "general",
                12.3);
        productService.editProduct(editProduct, productDTO.getId());
        ProductDTO getProduct = productService.getProductById(createProduct.getId());
        org.assertj.core.api.Assertions.assertThat(getProduct.getName()).isEqualTo("editProduct");
    }

    @Test
    void delete_should_be_successfully() throws Exception {
        ProductDTO productDTO = generateProduct();
        ProductDTO productCreate = productService.createProduct(productDTO);
        productService.deleteProduct(productCreate.getId());
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            productService.getProductById(productCreate.getId());
        });
    }

    @Test
    void getByName_should_be_successfully() throws Exception {
        ProductDTO productDTO = generateProduct();
        productService.createProduct(productDTO);
        ProductDTO productFoundByName = productService.getProductByName("new");
        Assertions.assertEquals(productDTO.getName(), productFoundByName.getName());
    }

    private ProductDTO generateProduct() {
        return new ProductDTO(null, "new", "newProduct", "category", "general", 12.3);
    }
}