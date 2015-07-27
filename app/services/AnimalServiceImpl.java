package services;

import models.Animal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class AnimalServiceImpl implements AnimalService {

    private static final Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

    @PersistenceContext
    EntityManager em;

    @Override
    public void addAnimal(Animal a) {
        em.persist(a);
        logger.trace("Added animal {}", a.toString());
    }

    @Override
    public Set<Animal> getAllAnimals() {
        CriteriaQuery<Animal> c = em.getCriteriaBuilder().createQuery(Animal.class);
        c.from(Animal.class);
        return new HashSet<Animal>(em.createQuery(c).getResultList());
    }

    @Override
    public void updateAnimal(Animal a) {
        em.merge(a);
    }

    @Override
    public void deleteAnimal(Animal a) {
        em.remove(a);
    }

}
