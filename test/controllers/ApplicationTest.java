package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;

import org.junit.Test;

import play.twirl.api.Html;

import java.util.TreeSet;

public class ApplicationTest {
    protected static final String APP_NAME = "Barn Management System";

    @Test
    public void indexTemplate() {
        running(fakeApplication(), new Runnable() {
            public void run() {
                // Form<Barn> form = Form.form(Barn.class);
                Html html = views.html.index.render(new TreeSet(), 1L);
                assertThat(contentType(html)).isEqualTo("text/html");
                assertThat(contentAsString(html)).contains(APP_NAME);
            }
        });
    }

}
