import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import configs.AppConfig;

import models.Animal;
import models.Barn;
import models.BarnForm;

import services.AnimalService;
import services.BarnService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.List;

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
        // Associate the animal with a Barn.
        Integer barnId = createBarn();
        animal.setBarn(new Barn(barnId));
        animalService.addAnimal(animal);
        assertFalse(animalService.getAllAnimals().isEmpty());
    }

    public Integer createBarn() {
        BarnForm barn = new BarnForm();
        barn.setName("Big Red Barn With Animals");
        barnService.addBarn(barn);
        return barnService.addBarn(barn);
    }

    @Test
    public void getAnimals() {
        createAnimal();
        List<Animal> animals = animalService.getAllAnimals();
        assertThat(animals.size()).isEqualTo(1);
    }

}
