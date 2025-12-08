package com.itschool.springapp.service;

import com.itschool.springapp.model.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO getProduct (long id);
    List<ProductDTO> findAll();
    List<ProductDTO> findByCategory(String category);
    ProductDTO addProduct (ProductDTO newProductDTO);
    ProductDTO updateProduct (long id, ProductDTO updatedProductDTO);
    void deleteProduct(long id);
}
