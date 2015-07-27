package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "barn")
public class Barn {

    @Id
    @Column(name = "barn_id")
    @GeneratedValue
    private Integer barnId;

    @Column(nullable = false)
    private String name;

    // Incredibly important to annotate any bi-directional relationships in Play with the
    // @JsonManagedReference for Parent and @JsonBackReference for Child (See Animal.java).
    // If you don't do this, the Jackson databinding with cause an infinite recursion
    // during JSON serialization!
    @OneToMany(mappedBy = "barn", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    private Set<Animal> animals;

    public Barn() {}

    public Barn(Integer id) {
        this.barnId = id;
    }

    public Integer getBarnId() {
        return barnId;
    }

    public void setBarnId(Integer id) {
        this.barnId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalsAsString() {
        if (animals == null || animals.size() == 0) {
            return "";
        }

        return animals.stream()
                      .map(Animal::toString)
                      .collect(Collectors.joining(", "));
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Barn)obj).getName());
    }

    @Override
    public String toString() {
        return name;
    }

}
