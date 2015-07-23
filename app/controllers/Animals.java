package controllers;

import services.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;

import play.data.Form;
import play.mvc.Result;
import play.libs.Json;
import models.Animal;

@org.springframework.stereotype.Controller
public class Animals {
    @Autowired
    AnimalService animalService;

    public Result index() {
        return play.mvc.Controller.ok(views.html.animals.render(Form.form(Animal.class)));
    }

    public Result addAnimal(){
        Form<Animal> form = Form.form(Animal.class).bindFromRequest();
        Animal barnAnimal = form.get();
        animalService.addAnimal(barnAnimal);
        return play.mvc.Controller.redirect(controllers.routes.Animals.index());
    }

    public Result listAnimals(){

        return play.mvc.Controller.ok(Json.toJson(animalService.getAllAnimals()));
    }
}
