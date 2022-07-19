package com.example.demo.controller;


import com.example.demo.exception.ProductNotFoundException;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.services.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;


@Slf4j
@RestController
@RequestMapping(value = "/api")
public class ProductController {

    @Autowired
    private ApplicationContext applicationContext;
    @Autowired
    private ProductService productService;

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
    private List<Product> getAll() {
        return productService.getAllProducts();
    }

    @PostMapping("/product")
    private Product newProduct(@RequestBody Product newProduct) {
        return productService.saveProduct(newProduct);
    }

    @GetMapping("/product/{id}")
    private Product getProductId(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping("/product/{id}")
    private Product editProduct(@RequestBody Product newProduct, @PathVariable Long id){
        return productService.editProduct(newProduct, id);
    }

    @GetMapping("/product")
    private Product getProductByName(@RequestParam String name){
        return productService.getProductByName(name);
    }

    @DeleteMapping("/product/{id}")
    private void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}
