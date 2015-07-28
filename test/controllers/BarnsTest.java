package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.mvc.Http.Status.SEE_OTHER;
import static play.test.Helpers.GET;
import static play.test.Helpers.callAction;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
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

public class BarnsTest {

    @Test
    public void testAddBarn() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "RedBarn1");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(routes.ref.Barns.addBarn(), fakeRequest);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    @Test
    public void testAddBarnNoName() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(routes.ref.Barns.addBarn(), fakeRequest);
                assertThat(status(result)).isEqualTo(400);
                assertThat(contentAsString(result)).contains("Barn must have a name.");
            }
        });
    }

    @Test
    public void testListBarns() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "RedBarn1");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);
                // Add a barn before listing them
                callAction(routes.ref.Barns.addBarn(), fakeRequest);

                Result result = callAction(routes.ref.Barns.listBarns());
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentType(result)).isEqualTo("application/json");
                assertThat(contentAsString(result)).startsWith("[");
                assertThat(contentAsString(result)).contains("RedBarn1");
            }
        });
    }

    @Test
    public void barnsRoute() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/barns"));
                assertThat(result).isNotNull();
            }
        });
    }

    @Test
    public void realBarnsRequest() {
        running(testServer(3333), new Runnable() {
            public void run() {
                assertThat(WS.url("http://localhost:3333/barns").get().get(5, TimeUnit.SECONDS).getStatus()).isEqualTo(OK);
            }
        });
    }
}
