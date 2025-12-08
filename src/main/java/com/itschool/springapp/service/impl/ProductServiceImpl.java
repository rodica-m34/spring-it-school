package com.itschool.springapp.service.impl;

import com.itschool.springapp.entity.Product;
import com.itschool.springapp.model.ProductDTO;
import com.itschool.springapp.repository.ProductRepository;
import com.itschool.springapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getProduct (long id) {
        Product foundProductEntity = productRepository.findById(id).orElseThrow();
        return toProductDTO(foundProductEntity);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<Product> allProductEntities = productRepository.findAll();
        return allProductEntities.stream().map(this::toProductDTO).toList();
    }

    @Override
    public List<ProductDTO> findByCategory(String category) {
        List<Product> productsByCategory = productRepository.findAll();
        return productsByCategory.stream().
                filter(p -> p.getCategory().equals(category)).map(this::toProductDTO).toList();
    }

    @Override
    public ProductDTO addProduct(ProductDTO newProductDTO) {
        Product productEntity = toProductEntity(newProductDTO);
        Product createdProductEntity = productRepository.save(productEntity);
        return toProductDTO(createdProductEntity);
    }

    @Override
    public ProductDTO updateProduct(long id, ProductDTO updatedProductDTO) {
        Product productEntity = toProductEntity(updatedProductDTO);
        productEntity.setId(id);
        Product updatedProductEntity = productRepository.save(productEntity);
        return toProductDTO(updatedProductEntity);
    }

    @Override
    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO toProductDTO (Product product) {
        return new ProductDTO(product.getId(), product.getName(),
                product.getCategory(), product.getPrice());
    }

    private Product toProductEntity(ProductDTO newProductDTO) {
        return new Product(newProductDTO.name(), newProductDTO.category(), newProductDTO.price());
    }
}
