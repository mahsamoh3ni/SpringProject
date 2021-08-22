package com.example.springmvc.springmvc.service;

import com.example.springmvc.springmvc.dto.ProductDTO;
import com.example.springmvc.springmvc.exception.ProductNotFoundException;
import com.example.springmvc.springmvc.model.ProductEntity;
import com.example.springmvc.springmvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductDTO createProduct(ProductDTO productDto) {
        ProductEntity entity = new ProductEntity(productDto.getName(),
                productDto.getDescription(),
                productDto.getCategory(),
                productDto.getType(),
                productDto.getPrice());

        ProductEntity productEntity = productRepository.save(entity);
        productDto.setId(productEntity.getId());
        return productDto;
    }

    @Transactional
    public void editProduct(ProductDTO editProduct, String id) throws Exception {
        Optional<ProductEntity> entity = productRepository.findById(id);
        if (entity.isPresent()) {
            ProductEntity productEntity = entity.get();
            productEntity.setName(editProduct.getName());
            productEntity.setCategory(editProduct.getCategory());
            productEntity.setType(editProduct.getType());
            productEntity.setDescription(editProduct.getDescription());
            productRepository.save(productEntity);
        } else {
            throw new ProductNotFoundException();
        }
    }

    @Transactional
    public void deleteProduct(String id) throws Exception {
        Optional<ProductEntity> productEntity = productRepository.findById(id);
        if (productEntity.isPresent())
            productRepository.delete(productEntity.get());
        else
            throw new ProductNotFoundException();
    }

    public ProductDTO getProductById(String id) throws Exception {
        if (productRepository.findById(id).isPresent()) {
            ProductEntity productEntity = productRepository.findById(id).get();
            ProductDTO productDTO = new ProductDTO(productEntity.getId(),
                    productEntity.getName(),
                    productEntity.getDescription(),
                    productEntity.getCategory(),
                    productEntity.getType(), productEntity.getPrice()
            );
            return productDTO;
        } else
            throw new ProductNotFoundException();
    }

    public ProductDTO getProductByName(String name) throws Exception {
        if (productRepository.findByName(name).isPresent()) {
            ProductEntity entity = productRepository.findByName(name).get();
            ProductDTO findByNameProduct = new ProductDTO(entity.getId(),
                    entity.getName(),
                    entity.getDescription(),
                    entity.getCategory(),
                    entity.getType(),
                    entity.getPrice());
            return findByNameProduct;
        } else
            throw new ProductNotFoundException();
    }


}

