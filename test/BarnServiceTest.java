import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import configs.AppConfig;

import models.Barn;
import models.BarnForm;

import services.BarnService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.util.Set;

@ContextConfiguration(classes = {
    AppConfig.class, TestDataConfig.class
})
public class BarnServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BarnService barnService;

    @Test
    public void createBarn() {
        BarnForm barn = new BarnForm();
        barn.setName("Big Red Barn With Animals");
        barnService.addBarn(barn);
        assertFalse(barnService.getAllBarns().isEmpty());
    }

    @Test
    public void getBarns() {
        createBarn();
        Set<Barn> barns = barnService.getAllBarns();
        assertThat(barns.size()).isEqualTo(1);
    }

}
