package controllers;

import static org.fest.assertions.Assertions.assertThat;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.GET;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.fakeRequest;
import static play.test.Helpers.route;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import configs.TestDataConfig;

import org.junit.Test;

import play.libs.ws.WS;
import play.mvc.Result;
import play.twirl.api.Html;

import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class ApplicationTest {
    protected static final String APP_NAME = "Barn Management System";

    @Test
    public void indexTemplate() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Html html = views.html.index.render(new TreeSet(), 1L);
                assertThat(contentType(html)).isEqualTo("text/html");
                assertThat(contentAsString(html)).contains(APP_NAME);
            }
        });
    }

    @Test
    public void indexRoute() {
        running(fakeApplication(TestDataConfig.appTestingConfMap), new Runnable() {
            public void run() {
                Result result = route(fakeRequest(GET, "/"));
                assertThat(result).isNotNull();
            }
        });
    }

    @Test
    public void realRequestMainPage() {
        running(testServer(3333), new Runnable() {
            public void run() {
                assertThat(WS.url("http://localhost:3333/").get().get(5, TimeUnit.SECONDS).getStatus()).isEqualTo(OK);
            }
        });
    }

}
