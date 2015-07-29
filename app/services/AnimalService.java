package services;

import models.Animal;

import java.util.SortedSet;

public interface AnimalService {

    /**
     * AddAnimal persists the animal instance to the barns db.
     *
     * @param animal The animal to be persisted.
     * @return Primary key value auto generated upon persist.
     * @throws IllegalArgumentException thrown when a null or nameless or duplicate animal is passed in.
     */
    Integer addAnimal(Animal animal);

    /**
     * Get All Animals fetches all the animals in the animal table.
     *
     * @return A SortedSet of all animals.
     */
    SortedSet<Animal> getAllAnimals();

}
