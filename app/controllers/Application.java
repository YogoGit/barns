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

import java.util.Set;

import services.BarnService;
import views.html.index;

@org.springframework.stereotype.Controller
public class Application {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private BarnService barnService;

    public Promise<Result> index() {
        Set<Barn> barns = barnService.getAllBarns();
        Promise<Result> promise = barnService.getValuation(barns).map(value -> {
            logger.info("Barn Valuation is: " + value);
            return play.mvc.Controller.ok(index.render(barns, value));
        });
        return promise;
    }

}