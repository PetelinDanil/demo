package com.example.demo.services;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long id);
    Product getProductByName(String name);
    Product saveProduct(Product product);
    String deleteProduct(Long id);
    Product editProduct(Product product, Long id);
}
