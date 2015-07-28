import configs.AppConfig;
import configs.DataConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import play.Application;
import play.GlobalSettings;
import play.mvc.Action;
import play.mvc.Http;

/**
 * Global settings with the Play Application.
 *
 * @author rlewan
 *
 */
public class Global extends GlobalSettings {

    private static final Logger logger = LoggerFactory.getLogger(Global.class);

    private ApplicationContext ctx;

    /*
     * Override here only to insert logging so we can see where the requests are going.
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Action onRequest(Http.Request request, java.lang.reflect.Method actionMethod) {
        MDC.put("method", actionMethod.toString().split(" ")[2]);
        logger.info("Http request {} has calling method {} ", request, actionMethod.toString().split(" ")[2]);
        return super.onRequest(request, actionMethod);
    }

    @Override
    public void onStart(Application app) {
        ctx = new AnnotationConfigApplicationContext(AppConfig.class, DataConfig.class);
    }

    @Override
    public <A> A getControllerInstance(Class<A> clazz) {
        return ctx.getBean(clazz);
        //return (A)clazz.newInstance();
    }

}

