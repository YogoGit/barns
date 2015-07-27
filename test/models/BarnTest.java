package models;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

public class BarnTest {

    @Test
    public void setBarnName() {
        Barn barn = new Barn();
        barn.setName("foo");
        assertThat(barn.getName()).isEqualTo("foo");
    }

}
