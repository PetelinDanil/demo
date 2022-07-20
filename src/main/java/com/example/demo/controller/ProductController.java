package com.example.demo.controller;


import com.example.demo.dto.ProductDto;
import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.mapper.ProductMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ProductController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    // использование конструктора вместо анотации Autowired
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    private void init() {

        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            log.info(" --- bean '{}'", beanName);
        }
        Product product = Product.builder().name("product1").description("desc1").remain(10).price(100f).build();
        productRepository.save(product);
        product = Product.builder().name("product2").description("desc1").remain(15).price(150f).build();
        productRepository.save(product);
        product = Product.builder().name("product3").description("desc1").remain(20).price(200f).build();
        productRepository.save(product);
    }

    @GetMapping("/hello")
    private String hello() {
        return "Hello world!";
    }

    @GetMapping("/products")
    private List<ProductDto> getAll() {
        List<Product> products = productService.getAllProducts();

        List<ProductDto> productsDto = products.stream().map(p -> {
            System.out.println(productMapper.toDto(p));
            return productMapper.toDto(p);
        }).collect(Collectors.toList());

        return productsDto;
    }

    @GetMapping("/product/{id}")
    private Product getProductId(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping("/product")
    private Product newProduct(@RequestBody Product newProduct) {
        return productService.saveProduct(newProduct);
    }

    @PutMapping("/product/{id}")
    private Product editProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        return productService.editProduct(newProduct, id);
    }

    @GetMapping("/product")
    private Product getProductByName(@RequestParam String name) {
        return productService.getProductByName(name);
    }

    @DeleteMapping("/product/{id}")
    private void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
