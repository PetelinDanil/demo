package com.example.demo.db;

import com.example.demo.mapper.ProductRowMapper;
import com.example.demo.model.Category;
import com.example.demo.model.Person;
import com.example.demo.model.Product;
import com.example.demo.model.Role;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.RoleRepository;
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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Configuration
@Component
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {
        Category category = Category.builder().name("category1").description("descCategory1").build();
        categoryRepository.save(category);

        Set<Role> rolesAdmin = new HashSet<>();
        Set<Role> rolesUser = new HashSet<>();

        Role roleAdmin = Role.builder().name("admin").description("administrator").build();
        roleRepository.save(roleAdmin);
        Role roleUser = Role.builder().name("user").description("user").build();
        roleRepository.save(roleUser);
        Role roleAnonym = Role.builder().name("anonymous").description("").build();
        roleRepository.save(roleAnonym);

        rolesAdmin.add(roleAdmin);
        rolesAdmin.add(roleUser);
        rolesUser.add(roleUser);


        Person person = Person.builder().email("123@gmail.com").firstName("").lastName("").login("user1").password("123").phoneNumber("00000001")
                .roleSet(rolesAdmin).build();
        personRepository.save(person);
        person = Person.builder().email("456@gmail.com").firstName("").lastName("").login("user2").password("123").phoneNumber("00000002")
                .roleSet(rolesUser).build();
        personRepository.save(person);
        person = Person.builder().email("789@gmail.com").firstName("").lastName("").login("user3").password("123").phoneNumber("00000003")
                .roleSet(rolesUser).build();
        personRepository.save(person);

        Product product = Product.builder().name("product1").description("desc1").remain(10).category(category).price(100f).build();
        productRepository.save(product);
        product = Product.builder().name("product2").description("desc2").remain(15).category(category).price(150f).build();
        productRepository.save(product);
        product = Product.builder().name("product3").description("desc3").remain(20).category(category).price(200f).build();
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
//            log.info("Preloading " + repository.findAll());
        };
    }
}
