package services;

import models.Animal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Service
public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

    @PersistenceContext
    private EntityManager em;

    /**
     * Persists an animal. Returns -1 if persist failed, returns a positive integer representing the generated ID if success.
     *
     */
    @Override
    @Transactional
    public Integer addAnimal(Animal a) {
        if (a == null || a.getName() == null || a.getName() == "") {
            throw new IllegalArgumentException();
        }

        Set<Animal> existingAnimals = a.getBarn().getAnimals();
        if (existingAnimals.stream().filter(animal -> animal.getName().equals(a.getName())).count() > 0) {
            throw new IllegalArgumentException("Cannot save an animal with duplicate name to the same barn.");
        }

        em.persist(a);
        logger.trace("Added animal {}", a.toString());
        return a.getId();
    }

    @Override
    public SortedSet<Animal> getAllAnimals() {
        CriteriaQuery<Animal> c = em.getCriteriaBuilder().createQuery(Animal.class);
        c.from(Animal.class);
        List<Animal> animalList = em.createQuery(c).getResultList();
        return new TreeSet<Animal>(animalList);
    }

}
