package com.example.demo.db;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

//@Configuration
@Component
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private ProductRepository productRepository;

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        Product product = Product.builder().name("product1").description("desc1").remain(10).price(100f).build();
        productRepository.save(product);
        product = Product.builder().name("product2").description("desc1").remain(15).price(150f).build();
        productRepository.save(product);
        product = Product.builder().name("product3").description("desc1").remain(20).price(200f).build();
        productRepository.save(product);


        return args -> {
            log.info("Preloading " + repository.findAll());
        };
    }
}
