package com.example.demo.mapper;

import com.example.demo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setDescription(rs.getString("description"));
        product.setName(rs.getString("name"));
        product.setRemain(rs.getInt("remain"));
        product.setPrice(rs.getFloat("price"));
        return product;
    }
}
