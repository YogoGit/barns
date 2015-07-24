package controllers;

import services.AnimalService;

import org.springframework.beans.factory.annotation.Autowired;

import play.data.Form;
import play.mvc.Result;
import play.libs.Json;
import models.Animal;
import models.AnimalForm;
import models.Barn;

@org.springframework.stereotype.Controller
public class Animals {
    @Autowired
    AnimalService animalService;

    public Result index() {
        return play.mvc.Controller.ok(views.html.animals.render(Form.form(AnimalForm.class)));
    }

    public Result addAnimal(){
        Form<AnimalForm> form = Form.form(AnimalForm.class).bindFromRequest();
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
