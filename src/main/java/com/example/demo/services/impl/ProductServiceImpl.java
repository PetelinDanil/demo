package com.example.demo.services.impl;

import com.example.demo.dto.ProductDto;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private ProductMapper productMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.productRepository = repository;
        this.productMapper = mapper;
    }

    @Override
    public Product saveProduct(Product product){
        return this.productRepository.save(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts(){
        List<Product> allProducts =  productRepository.findAll();
        return allProducts;
    }

    @Override
    public Product getProductById(Long id){
        return this.productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public Product getProductByName(String name){
        return this.productRepository.findByName(name);
    }

    @Override
    public String deleteProduct(Long id){
        if(this.productRepository.existsById(id)){
            this.productRepository.deleteById(id);
            return "product removed !! " + id;
        }
        throw new ProductNotFoundException(id);
    }

    @Override
    public Product editProduct(Product newProduct, Long id) {
        return productRepository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setRemain(newProduct.getRemain());
            product.setPrice(newProduct.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new ProductNotFoundException(id));
//          .orElseGet(() -> {
//            newProduct.setId(id);
//            return productRepository.save(newProduct);
//        });
    }
}
