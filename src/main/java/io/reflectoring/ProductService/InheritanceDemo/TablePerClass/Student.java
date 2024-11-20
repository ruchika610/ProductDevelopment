package io.reflectoring.ProductService.InheritanceDemo.TablePerClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "tpc_student")
public class Student extends User {
    private double psp;
    private double attendence;
}
