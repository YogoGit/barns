package models;

import play.data.validation.Constraints;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
@Table(name = "animal")
public class Animal {

    @Id
    @GeneratedValue
    public String id;

    @Constraints.Required(message = "The name is required")
    public String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "animal")
    public List<Product> products;

}
