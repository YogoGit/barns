package models;

import java.util.List;

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
    @Column(name="barn_id")
    @GeneratedValue
    private Integer barnId;

    private String name;

    @OneToMany(mappedBy="barn", fetch=FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Animal> animals;

    public Barn(){}

    public Barn(Integer id){
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

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
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