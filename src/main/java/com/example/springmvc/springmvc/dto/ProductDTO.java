package com.example.springmvc.springmvc.dto;

public class ProductDTO {

    private String id;
    private String name;
    private String description;
    private String category;
    private String type;
    private Double price;

    public ProductDTO(String id, String name, String description, String category, String type, Double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.type = type;
        this.price = price;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
