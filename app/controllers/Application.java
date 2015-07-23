package controllers;

import models.Barn;
import models.BarnForm;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;

import java.util.Set;

import services.BarnService;
import views.html.index;

@org.springframework.stereotype.Controller
public class Application {

    @Autowired
    private BarnService barnService;

    public Result index() {
        Set<Barn> barns = barnService.getAllBarns();
        return play.mvc.Controller.ok(index.render(barns));
    }

}