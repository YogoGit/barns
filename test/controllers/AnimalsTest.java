package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assume.assumeTrue;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.GET;
import static play.test.Helpers.callAction;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.test.Helpers.testServer;

import configs.AppConfig;
import configs.TestDataConfig;

import models.Animal;
import models.Barn;
import models.BarnForm;

import services.BarnService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import play.libs.ws.WS;
import play.mvc.Result;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration(classes = {
    AppConfig.class, TestDataConfig.class
})
public class AnimalsTest extends AbstractTransactionalJUnit4SpringContextTests{

    @Rule
    public TestName testName = new TestName();

    @Autowired
    private BarnService barnService;

    @Before
    public void setup() {
        createBarn();
    }

    private Barn testBarn;

    public void createBarn() {
        // Must put a Barn in the DB for Animal to associate with.
        if(testName.getMethodName().equals("callAddAnimal")){
            BarnForm barnForm = new BarnForm();
            barnForm.setName("Big Red Barn With Animals");
            testBarn = barnService.addBarn(barnForm);
        }
    }

    @Test
    @Transactional
    public void callAddAnimal() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "Donkey");
                formParams.put("barnId", "1");
                formParams.put("quantity", "5");
                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(controllers.routes.ref.Animals.addAnimal(), fakeRequest);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    @Test
    public void animalsRoute() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/animals"));
                assertThat(result).isNotNull();
            }
        });
    }

    @Test
    public void realAnimalsRequest() {
        running(testServer(3333), new Runnable() {
            public void run() {
                assertThat(WS.url("http://localhost:3333/animals").get().get(5, TimeUnit.SECONDS).getStatus()).isEqualTo(OK);
            }
        });
    }

}
