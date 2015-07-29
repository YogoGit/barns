package services;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import configs.AppConfig;
import configs.TestDataConfig;

import models.Animal;
import models.Barn;
import models.BarnForm;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

@ContextConfiguration(classes = {
    AppConfig.class, TestDataConfig.class
})
public class AnimalServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private AnimalService animalService;

    @Autowired
    private BarnService barnService;

    @Test
    public void getAllAnimalsWhenThereAreNone() {
        assertTrue(animalService.getAllAnimals().size() == 0);
    }

    @Test
    public void createAnimalTwice() {
        assertTrue(animalService.getAllAnimals().size() == 0);
        Animal animal = new Animal();
        animal.setName("Donkey");
        animal.setQuantity(99);
        // Associate the animal with a Barn.
        Barn barn = createBarn();
        animal.setBarn(barn);
        barn.getAnimals().add(animal);
        // Saving the barn saves its animals (via cascade of OneToMany)
        barnService.save(barn);
        assertTrue(animalService.getAllAnimals().size() == 1);

        Animal secondAnimal = new Animal();
        secondAnimal.setName("Llama");
        secondAnimal.setQuantity(23);
        secondAnimal.setBarn(barn);
        barn.getAnimals().add(secondAnimal);
        barnService.save(barn);
        assertTrue(animalService.getAllAnimals().size() == 2);
    }

    @Test
    public void createDuplicateAnimalSameBarn() {
        assertTrue(animalService.getAllAnimals().size() == 0);
        Animal animal = new Animal();
        animal.setName("Donkey");
        animal.setQuantity(99);
        // Associate the animal with a Barn.
        Barn barn = createBarn();
        animal.setBarn(barn);
        barn.getAnimals().add(animal);
        // Saving the barn saves its animals (via cascade of OneToMany)
        barnService.save(barn);
        assertTrue(animalService.getAllAnimals().size() == 1);

        Animal secondAnimal = new Animal();
        secondAnimal.setName("Donkey");
        secondAnimal.setQuantity(23);
        secondAnimal.setBarn(barn);
        try {
            animalService.addAnimal(secondAnimal);
            // Can't add 2 of the same animal to the same barn. Expecting exception.
            fail();
        } catch (IllegalArgumentException ignored) {
        }

    }

    // Helper method
    private Barn createBarn() {
        BarnForm barnForm = new BarnForm();
        barnForm.setName("Big Red Barn With Animals");
        Barn b = barnService.addBarn(barnForm);
        return b;
    }

    @Test
    public void testNullAndNamelessAnimal() {
        Animal animal = new Animal();
        try {
            animalService.addAnimal(animal);
            // Shouldn't reach this point since adding the nameless animal ought to throw IllegalArgumentException.
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    public void testNullAnimal() {
        try {
            animalService.addAnimal(null);
            // Shouldn't reach this point since adding the null animal ought to throw IllegalArgumentException.
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

}
