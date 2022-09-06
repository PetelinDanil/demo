package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "products")
@Data
@Builder(toBuilder = false)
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min=5, max=60)
    @NotNull
    @Pattern(regexp = "^[A-Za-z0-9.:\\- ]+$")
    private String name;

    @NotBlank
    @Size(min=5, max=60)
    @Pattern(regexp = "^[A-Za-z0-9.,-; ]+$")
    private String description;

    private Integer remain;

    private Float price;

    @ManyToOne
//    @JsonManagedReference
    private Category category;

}
