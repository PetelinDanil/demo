package com.example.demo.mapper;

import com.example.demo.dto.ProductDto;
import com.example.demo.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    public Product toEntity(ProductDto dto){
        return Objects.isNull(dto) ? null : mapper.map(dto, Product.class);
    }

    public ProductDto toDto(Product product) {
        return Objects.isNull(product) ? null : mapper.map(product, ProductDto.class);
    }
}
