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
        assertNotNull(productMapper);

        ProductDto productDto = new ProductDto(1l, "prod2", "desc1", 5, 101.5f);
        Product product = productMapper.toEntity(productDto);
        assertEquals(productDto.getId(), product.getId());
        assertEquals(productDto.getName(), product.getName());
        assertEquals(productDto.getDescription(), product.getDescription());
        assertEquals(productDto.getRemain(), product.getRemain());
        assertEquals(productDto.getPrice(), product.getPrice());

//        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
//        for (String beanName : allBeanNames) {
//
//            System.out.println(" --- bean: " + beanName);
//        }
    }

    @Test
    void toDto() {
        Product product = new Product(2l, "prod2", "desc2", 3, 75.0f);
        ProductDto productDto = productMapper.toDto(product);
        assertEquals(product.getId(), productDto.getId());
        assertEquals(product.getName(), productDto.getName());
        assertEquals(product.getDescription(), productDto.getDescription());
        assertEquals(product.getRemain(), productDto.getRemain());
        assertEquals(product.getPrice(), productDto.getPrice());
    }
}
