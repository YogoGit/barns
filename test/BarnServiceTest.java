import configs.AppConfig;
import models.Barn;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import services.BarnService;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

@ContextConfiguration(classes={AppConfig.class, TestDataConfig.class})
public class BarnServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BarnService barnService;

    @Test
    public void createBarn() {
        Barn barn = new Barn();
        barn.name = "Big Red Barn With Animals";
        barnService.addBarn(barn);
        assertThat(barn.id).isNotNull();
    }

    @Test
    public void getBars() {
        createBarn();
        List<Barn> barns = barnService.getAllBarns();
        assertThat(barns.size()).isEqualTo(1);
    }

}