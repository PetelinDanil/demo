package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class ProductMapperTest {

//    @Autowired
//    private ApplicationContext applicationContext;
//    @Autowired
    private ProductMapper productMapper = new ProductMapper(new ModelMapper());

    @Test
    void toEntity() {
        ProductDto productDto = new ProductDto(1l, "prod1", "desc1", 5, 101.5f);
        Product product = productMapper.toEntity(productDto);
        assertEquals(1, product.getId());
        assertEquals("prod1", product.getName());
        assertEquals("desc1", product.getDescription());
        assertEquals(5, product.getRemain());
        assertEquals(101.5f, product.getPrice());

//        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
//        for (String beanName : allBeanNames) {
//
//            System.out.println(" --- bean: " + beanName);
//        }
        System.out.println(productMapper);
        assertNotNull(productMapper);
    }

    @Test
    void toDto() {
        Product product = new Product(2l, "prod2", "desc2", 3, 75.0f);
        ProductDto productDto = productMapper.toDto(product);
        assertEquals(2, productDto.getId());
        assertEquals("prod2", productDto.getName());
        assertEquals("desc2", productDto.getDescription());
        assertEquals(3, productDto.getRemain());
        assertEquals(75.0f, productDto.getPrice());
    }
}
