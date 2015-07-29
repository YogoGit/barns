package models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.SortNatural;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "barn")
public class Barn implements Comparable<Barn> {

    @Id
    @Column(name = "barn_id")
    @GeneratedValue
    private Integer barnId;

    @Column(nullable = false, unique = true)
    private String name;

    // Important to annotate any bi-directional relationships in Play with @JsonManagedReference
    @OneToMany(mappedBy = "barn", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JsonManagedReference
    @OrderBy("name ASC")
    @SortNatural
    private SortedSet<Animal> animals;

    public Barn() {
        animals = new TreeSet<>(Comparator.comparing(Animal::getName));
    }

    public Barn(Integer id) {
        this();
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
        return animals.stream()
                      .map(Animal::toString)
                      .collect(Collectors.joining(", "));
    }

    public SortedSet<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(SortedSet<Animal> animals) {
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

    @Override
    public int compareTo(Barn o) {
        return this.name.compareTo(o.getName());
    }

}
