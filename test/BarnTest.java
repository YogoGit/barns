import configs.AppConfig;
import models.Barn;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.fest.assertions.Assertions.assertThat;

@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class BarnTest extends AbstractTransactionalJUnit4SpringContextTests {
 
    @Test
    public void setBarName() {
        Barn bar = new Barn();
        bar.name = "foo";
        assertThat(bar.name).isEqualTo("foo");
    }

}
