package services;

import models.Animal;

import java.util.Set;

public interface AnimalService {
    public void addAnimal(Animal a);

    public Set<Animal> getAllAnimals();

    public void updateAnimal(Animal a);

    public void deleteAnimal(Animal a);

}
