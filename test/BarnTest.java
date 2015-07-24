import static org.fest.assertions.Assertions.assertThat;

import configs.AppConfig;

import models.Barn;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {
    AppConfig.class, TestDataConfig.class
})
public class BarnTest {

    @Test
    public void setBarnName() {
        Barn barn = new Barn();
        barn.setName("foo");
        assertThat(barn.getName()).isEqualTo("foo");
    }

}
