package controllers;

import models.Animal;
import models.AnimalForm;
import models.Barn;

import services.AnimalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import play.data.Form;
import play.libs.Json;
import play.mvc.Result;

import views.html.animals;

@org.springframework.stereotype.Controller
public class Animals {

    private static final Logger logger = LoggerFactory.getLogger(Animals.class);

    @Autowired
    AnimalService animalService;

    public Result index() {
        return play.mvc.Controller.ok(views.html.animals.render(Form.form(AnimalForm.class)));
    }

    public Result addAnimal(){
        Form<AnimalForm> form = Form.form(AnimalForm.class).bindFromRequest();
        if (form.hasErrors()) {
            return play.mvc.Controller.badRequest(animals.render(form));
        }
        AnimalForm formAnimal = form.get();
        Animal animal = new Animal();
        animal.setName(formAnimal.getName());
        animal.setBarn(new Barn(formAnimal.getBarnId()));
        animalService.addAnimal(animal);
        return play.mvc.Controller.redirect(controllers.routes.Application.index());
    }

    public Result listAnimals(){
        return play.mvc.Controller.ok(Json.toJson(animalService.getAllAnimals()));
    }
}
