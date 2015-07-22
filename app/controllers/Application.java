package controllers;


import models.Barn;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;

import services.AnimalService;
import services.BarnService;
import views.html.index;

@org.springframework.stereotype.Controller
public class Application {

    @Autowired
    private BarnService barnService;

    @Autowired AnimalService animalService;

    public Result index() {
        return play.mvc.Controller.ok(index.render(Form.form(Barn.class)));
    }

    public Result addBarn() {
        Form<Barn> form = Form.form(Barn.class).bindFromRequest();
        Barn bar = form.get();
        barnService.addBarn(bar);
        return play.mvc.Controller.redirect(controllers.routes.Application.index());
    }

    public Result listBarns() {
        return play.mvc.Controller.ok(Json.toJson(barnService.getAllBarns()));
    }

    public Result listAnimals(){
        return play.mvc.Controller.ok(Json.toJson(animalService.getAllAnimals()));
    }

}