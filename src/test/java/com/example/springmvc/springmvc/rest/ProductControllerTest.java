package com.example.springmvc.springmvc.rest;


import com.example.springmvc.springmvc.dto.ProductDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithAnonymousUser;


import static io.restassured.RestAssured.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest {

    @LocalServerPort
    private int port;

    private static ProductDTO requestBody = new ProductDTO(null, "sdf", "sfd", "sdvc", "sdfs", 12.3);

    @Test
    void getProduct_should_be_successfully() {

        ProductDTO postProduct = postProduct();

        ProductDTO productDTO = get("http://localhost:" + port + "/api/products/" + postProduct.getId())
                .then()
                .statusCode(200)
                .extract()
                .as(ProductDTO.class);

        Assertions.assertThat(productDTO.getId()).isEqualTo(postProduct.getId());
        Assertions.assertThat(productDTO.getName()).isEqualTo(requestBody.getName());
        Assertions.assertThat(productDTO.getCategory()).isEqualTo(requestBody.getCategory());
        Assertions.assertThat(productDTO.getDescription()).isEqualTo(requestBody.getDescription());
        Assertions.assertThat(productDTO.getType()).isEqualTo(requestBody.getType());
        Assertions.assertThat(productDTO.getPrice()).isEqualTo(requestBody.getPrice());

    }

    @Test
    void createProduct_should_be_successfully() {
        ProductDTO productDTO = postProduct();
        Assertions.assertThat(productDTO.getName()).isEqualTo(requestBody.getName());
        Assertions.assertThat(productDTO.getCategory()).isEqualTo(requestBody.getCategory());
        Assertions.assertThat(productDTO.getDescription()).isEqualTo(requestBody.getDescription());
        Assertions.assertThat(productDTO.getType()).isEqualTo(requestBody.getType());
        Assertions.assertThat(productDTO.getPrice()).isEqualTo(requestBody.getPrice());

    }


    @Test
    void updateProduct_should_be_successfully() {

        ProductDTO postProduct = postProduct();

        String requestBody = "{\r\n" +
                " \"id\":\"null\",\r\n" +
                " \"name\":\"mahsa\",\r\n" +
                " \"category\":\"asd\",\r\n" +
                " \"description\":\"sdfs\"\r\n" +
                " \"type\":\"asd\"\r\n" +
                " \"price\":\45.3\\r\n" +
                "}";

        ProductDTO productDTO = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("http://localhost:" + port + "/api/products/" + postProduct.getId())
                .then()
                .assertThat()
                .statusCode(200)
                .extract()
                .as(ProductDTO.class);

        Assertions.assertThat(productDTO.getId()).isEqualTo(postProduct.getId());
        Assertions.assertThat(productDTO.getName()).isEqualTo("asd");
        Assertions.assertThat(productDTO.getCategory()).isEqualTo("eqw");
        Assertions.assertThat(productDTO.getDescription()).isEqualTo("qwe");

    }

    ProductDTO postProduct() {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("http://localhost:" + port + "/api/products")
                .then()
                .statusCode(200)
                .extract()
                .as(ProductDTO.class);
    }
}