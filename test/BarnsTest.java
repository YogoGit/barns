import org.junit.Test;
import models.Barn;
import play.data.Form;
import play.libs.ws.WS;
import play.mvc.Result;
import play.test.FakeRequest;
import play.twirl.api.Html;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

public class BarnsTest {

    @Test
    public void callAddBarn() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "RedBarn1");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                Result result = callAction(controllers.routes.ref.Barns.addBarn(), fakeRequest);
                assertThat(status(result)).isEqualTo(SEE_OTHER);
            }
        });
    }

    @Test
    public void callListBarns() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                Map<String, String> formParams = new HashMap<String, String>();
                formParams.put("name", "RedBarn1");

                FakeRequest fakeRequest = fakeRequest().withFormUrlEncodedBody(formParams);

                callAction(controllers.routes.ref.Barns.addBarn(), fakeRequest);

                Result result = callAction(controllers.routes.ref.Barns.listBarns());
                assertThat(status(result)).isEqualTo(OK);
                assertThat(contentType(result)).isEqualTo("application/json");
                assertThat(contentAsString(result)).startsWith("[");
                assertThat(contentAsString(result)).contains("RedBarn1");
            }
        });
    }

    @Test
    public void barnsRoute() {
        running(fakeApplication(), new Runnable() {
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
