package services;

import models.Animal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService{

    @PersistenceContext
    EntityManager em;

    @Override
    public void addAnimal(Animal a) {

    }

    @Override
    public List<Animal> getAllAnimals() {
        return null;
    }

    @Override
    public void updateAnimal(Animal a) {

    }

    @Override
    public void deleteAnimal(Animal a) {

    }

}
