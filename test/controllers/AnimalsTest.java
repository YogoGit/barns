package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import static play.test.Helpers.status;
import static play.test.Helpers.testServer;

import configs.TestDataConfig;

import org.junit.Test;

import play.libs.ws.WS;
import play.mvc.Result;
import play.test.FakeRequest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AnimalsTest {

    @Test
    public void testAddAnimalNoBarn() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "Donkey");
                formParams.put("quantity", "5");
                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(routes.ref.Animals.addAnimal(), fakeRequest);
                assertThat(status(result)).isEqualTo(400);
                assertThat(contentAsString(result)).contains("You must specify a barn first.");
            }
        });
    }

    @Test
    public void testAddAnimalNoNameNoQuantity() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "");
                formParams.put("quantity", "");
                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(routes.ref.Animals.addAnimal(), fakeRequest);
                assertThat(status(result)).isEqualTo(400);
                assertThat(contentAsString(result)).contains("You must enter an animal name.");
                assertThat(contentAsString(result)).contains("You must enter a quantity.");
            }
        });
    }

    @Test
    public void testAddAnimalNoParams() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(routes.ref.Animals.addAnimal(), fakeRequest);
                assertThat(status(result)).isEqualTo(400);
                assertThat(contentAsString(result)).contains("You must enter an animal name.");
                assertThat(contentAsString(result)).contains("You must enter a quantity.");
            }
        });
    }

    @Test
    public void testAnimalsRoute() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
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
