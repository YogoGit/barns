package services;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import configs.AppConfig;
import configs.TestDataConfig;

import models.Barn;
import models.BarnForm;

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
    public void testAddBarn() {
        assertTrue(barnService.getAllBarns().isEmpty());
        BarnForm barn = new BarnForm();
        barn.setName("Big Red Barn With Animals");
        barnService.addBarn(barn);
        assertFalse(barnService.getAllBarns().isEmpty());
    }

    @Test
    public void testDuplicateBarns() {
        BarnForm barn = new BarnForm();
        barn.setName("Red Barn Without Animals");
        barnService.addBarn(barn);

        BarnForm barn2 = new BarnForm();
        barn2.setName("Red Barn Without Animals");
        try{
            barnService.addBarn(barn2);
            fail();
        } catch (javax.persistence.PersistenceException ignored) {
        }
    }

    @Test
    public void testGetAllBarns() {
        testAddBarn();
        Set<Barn> barns = barnService.getAllBarns();
        assertThat(barns.size()).isEqualTo(1);
    }

    @Test
    public void testGetBarnById() {
        // save a barn
        Barn barn = new Barn();
        barn.setName("Another Red Barn");
        Barn savedBarn = barnService.save(barn);
        assertNotNull(savedBarn.getBarnId());

        // fetch the barn
        Barn fetchedBarn = barnService.getBarnById(savedBarn.getBarnId());
        assertEquals(fetchedBarn.getBarnId(), savedBarn.getBarnId());
    }

    @Test
    public void testSaveNamelessBarn() {
        BarnForm barnForm = new BarnForm();
        try {
            barnService.addBarn(barnForm);
            fail();
        } catch (javax.persistence.PersistenceException e) {
        }
    }

    @Test
    public void testSaveNullBarn() {
        try {
            barnService.addBarn(null);
            // expecting an exception here.
            fail();
        } catch (IllegalArgumentException ignored) {
        }
    }

}
