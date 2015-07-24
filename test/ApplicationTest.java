import static play.test.Helpers.fakeApplication;

import org.junit.Test;

import play.twirl.api.Html;

import java.util.HashSet;

// todo: not using the right spring context when using fakeApplication()
public class ApplicationTest {
    @Test
    public void indexTemplate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                // Form<Barn> form = Form.form(Barn.class);
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
