import static org.junit.Assert.assertEquals;

import models.Animal;
import models.Barn;

import org.junit.Test;

public class AnimalTest {

    @Test
    public void testEntityObject(){

        Animal a = new Animal();
        a.setName("Donkey");
        a.setBarn(new Barn(1));
        assertEquals(a.getName(), "Donkey");
        assertEquals(a.getBarn().getBarnId(), new Integer(1));
    }
}
