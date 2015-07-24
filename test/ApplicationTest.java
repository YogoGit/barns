import models.Barn;
import org.junit.Test;
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

// todo: not using the right spring context when using fakeApplication()
public class ApplicationTest {
    private static String APP_NAME = "Barn Management System";

    @Test
    public void indexTemplate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                //Form<Barn> form = Form.form(Barn.class);
                Html html = views.html.index.render(new HashSet());
                assertThat(contentType(html)).isEqualTo("text/html");
                assertThat(contentAsString(html)).contains(APP_NAME);
            }
        });
    }

//    @Test
//    public void callIndex() {
//        Result result = callAction(controllers.routes.ref.Application.index());
//        assertThat(status(result)).isEqualTo(OK);
//        assertThat(contentType(result)).isEqualTo("text/html");
//        assertThat(charset(result)).isEqualTo("utf-8");
//        assertThat(contentAsString(result)).contains(APP_NAME);
//    }

}
