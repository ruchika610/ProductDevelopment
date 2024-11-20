package io.reflectoring.ProductService.Models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel {

    @Getter
    @Setter
    private String title;
    private String description;
    private String image; //url of image so string

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "category")
    private Category category;

    @OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private Price price = new Price();


}
