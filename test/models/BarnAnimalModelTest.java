package models;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BarnAnimalModelTest {

    @Test
    public void testBarnModelWithAnimals() {
        Barn barn = new Barn();
        barn.setName("foo");
        assertThat(barn.getName()).isEqualTo("foo");

        Animal dog = new Animal();
        dog.setName("dog");
        dog.setQuantity(1);
        dog.setBarn(barn);

        Animal cow = new Animal();
        cow.setName("cow");
        cow.setQuantity(2);
        cow.setBarn(barn);

        // Sorted by natural ordering. Cow should come before Dog (alphabetically by name)
        assertTrue(barn.getAnimalsAsString().equals("cow(2), dog(1)"));
    }

}
