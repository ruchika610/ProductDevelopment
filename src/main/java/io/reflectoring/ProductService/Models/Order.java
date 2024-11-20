package io.reflectoring.ProductService.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;

import lombok.Setter;
import java.util.List;


@Entity(name = "orders")
@Getter
@Setter
public class Order extends BaseModel{

    @ManyToMany
    @JoinTable(//if we want to name mapped table as per our convenience then you can do this
            name = "product_orders",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> product;
}
