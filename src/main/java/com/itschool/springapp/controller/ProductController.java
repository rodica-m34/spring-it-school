package com.itschool.springapp.controller;

import com.itschool.springapp.model.ProductDTO;
import com.itschool.springapp.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("{id}")
    public ProductDTO getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }

    @GetMapping
    public List<ProductDTO> getAllProducts(){
        return productService.findAll();
    }

    @GetMapping(value = "category/{category}")
    public List<ProductDTO> findByCategory(@PathVariable String category){
        return productService.findByCategory(category);
    }

    @PostMapping
    public ProductDTO addProduct (@RequestBody ProductDTO newProductDTO){
        return productService.addProduct(newProductDTO);
    }

    @PutMapping("{id}")
    public ProductDTO updateProduct (long id, ProductDTO updatedProductDTO){
        return productService.updateProduct(id, updatedProductDTO);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable long id){
        productService.deleteProduct(id);
    }
}
