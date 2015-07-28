package services;

import static org.junit.Assert.assertTrue;

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
    public void createAnimal() {
        Animal animal = new Animal();
        animal.setName("Donkey");
        animal.setQuantity(5);
        // Associate the animal with a Barn.
        Barn barn = createBarn();
        animal.setBarn(barn);
        // Saving the barn saves its animals (via cascade of OneToMany)
        barnService.save(barn);
        assertTrue(animalService.getAllAnimals().size() > 0);
    }

    // Helper method
    private Barn createBarn() {
        BarnForm barnForm = new BarnForm();
        barnForm.setName("Big Red Barn With Animals");
        Barn b = barnService.addBarn(barnForm);
        return b;
    }

    @Test
    public void testNullAndNamelessAnimal(){
        Animal animal = new Animal();
        boolean namelessAnimalThrewException = false;
        try{
            animalService.addAnimal(animal);
        } catch (javax.persistence.PersistenceException e){
            namelessAnimalThrewException = true;
        }
        assertTrue(namelessAnimalThrewException);

    }

}
