package services;

import models.Animal;

import java.util.List;

public interface AnimalService {
    public void addAnimal(Animal a);
    public List<Animal> getAllAnimals();
    public void updateAnimal(Animal a);
    public void deleteAnimal(Animal a);

}
