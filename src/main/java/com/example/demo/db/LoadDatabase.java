package com.example.demo.db;

import com.example.demo.mapper.ProductRowMapper;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//@Configuration
@Component
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        Product product = Product.builder().name("product1").description("desc1").remain(10).price(100f).build();
        productRepository.save(product);
        product = Product.builder().name("product2").description("desc1").remain(15).price(150f).build();
        productRepository.save(product);
        product = Product.builder().name("product3").description("desc1").remain(20).price(200f).build();
        productRepository.save(product);

        System.out.println("--- Testing rowMapper");
        Connection connect = null;

//        Statement stmt = null;
//        ResultSet rs = null;
        try {
            Class.forName("org.postgresql.Driver");
            connect = dataSource.getConnection();

//            stmt = connect.createStatement();
//            rs = stmt.executeQuery("select * from products");
            List<Product> productList = jdbcTemplate.query("select * from products", new ProductRowMapper());
            System.out.println(productList);

//            List<Product> productList1 = jdbcTemplate.query("select * from products", (rs, rowNum) -> {
//                Product product1 = new Product();
//                product1.setId(rs.getLong("id"));
//                product1.setDescription(rs.getString("description"));
//                product1.setName(rs.getString("name"));
//                product1.setRemain(rs.getInt("remain"));
//                product1.setPrice(rs.getFloat("price"));
//                return product1;
//            });
//
//            System.out.println(productList1);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }finally{
            try {
//                if(rs != null) rs.close();
//                if(stmt != null) stmt.close();
                if(connect != null) connect.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return args -> {
            log.info("Preloading " + repository.findAll());
        };
    }
}
