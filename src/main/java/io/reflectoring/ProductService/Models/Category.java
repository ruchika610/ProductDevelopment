package io.reflectoring.ProductService.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Category extends BaseModel{
    @Column
    private String name;
    @OneToMany(mappedBy = "category")
    private List<Product> products;
    //this is the same relation being mapped by
    // category attribute in the other (product class) class.
}
