import org.junit.Test;
import play.data.Form;
import play.libs.ws.WS;
import play.mvc.Result;
import play.test.FakeRequest;
import play.twirl.api.Html;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.*;

import models.Barn;

import java.util.HashSet;

// todo: not using the right spring context when using fakeApplication()
public class ApplicationTest {
    protected static final String APP_NAME = "Barn Management System";

    @Test
    public void indexTemplate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                // Form<Barn> form = Form.form(Barn.class);
                Html html = views.html.index.render(new HashSet(), 1L);
                assertThat(contentType(html)).isEqualTo("text/html");
                assertThat(contentAsString(html)).contains(APP_NAME);
            }
        });
    }

}
