package services;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import configs.AppConfig;
import configs.TestDataConfig;

import models.Animal;
import models.Barn;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Set;

@ContextConfiguration(classes = {
    AppConfig.class, TestDataConfig.class
})
public class AnimalServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private BarnService barnService;

    @Test
    public void createAnimal() {
        Animal animal = new Animal();
        animal.setName("Donkey");
        animal.setQuantity(5);
        // Associate the animal with a Barn.
        Barn barn = createBarn();
        animal.setBarn(barn);
        // Saving the barn saves its animals (via cascade of OneToMany)
        barnService.save(barn);
        assertFalse(animalService.getAllAnimals().isEmpty());
    }

    public Barn createBarn() {
        Barn barn = new Barn();
        barn.setName("Big Red Barn With Animals");
        barnService.save(barn);
        return barn;
    }

    @Test
    public void getAnimals() {
        createAnimal();
        Set<Animal> animals = animalService.getAllAnimals();
        assertThat(animals.size()).isEqualTo(1);
    }

}
