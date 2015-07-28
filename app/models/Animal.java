package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "animal")
public class Animal implements Comparable<Animal> {

    @Id
    @Column(name = "animal_id")
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "barn_id", nullable = false)
    @JsonBackReference
    private Barn barn;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Barn getBarn() {
        return barn;
    }

    public void setBarn(Barn barn) {
        this.barn = barn;
        if (barn != null) {
            barn.getAnimals().add(this);
        }
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int count) {
        this.quantity = count;
    }

    @Override
    public String toString() {
        return name + "(" + quantity + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((barn == null) ? 0 : barn.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + quantity;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Animal other = (Animal)obj;
        if (barn == null) {
            if (other.barn != null)
                return false;
        } else if (!barn.equals(other.barn))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (quantity != other.quantity)
            return false;
        return true;
    }

    @Override
    public int compareTo(Animal a) {
        return this.name.compareTo(a.getName());
    }

}
