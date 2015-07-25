package controllers;

import models.Barn;
import models.BarnForm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.F.Promise;
import play.libs.Json;
import play.mvc.Result;
import static play.mvc.Controller.ok;

import java.util.Set;

import services.BarnService;
import views.html.index;

@org.springframework.stereotype.Controller
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private BarnService barnService;

    /**
     * Index is the entrypoint for this application. It loads all the Barns
     * in the DB and also calls a node.js webservice (via barnService) to
     * determine the valuation of the Barns.
     *
     * @return Result of the index render, given as Promise<Result> due to async.
     */
    public Promise<Result> index() {
        Set<Barn> barns = barnService.getAllBarns();
        Promise<Result> promise = barnService.getValuation(barns).map(value -> {
            logger.info("Barn Valuation is {}", value);
            return ok(index.render(barns, value));
        });
        return promise;
    }

}