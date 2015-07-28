package controllers;

import models.Animal;
import models.AnimalForm;
import models.Barn;

import services.AnimalService;
import services.BarnService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Set;

@org.springframework.stereotype.Controller
public class Animals extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Animals.class);

    @Autowired
    private AnimalService animalService;

    @Autowired
    private BarnService barnService;

    public Result index() {
        return ok(views.html.animals.render(Form.form(AnimalForm.class)));
    }

    public Result addAnimal() {
        Form<AnimalForm> form = Form.form(AnimalForm.class).bindFromRequest();
        if (form.hasErrors()) {
            logger.debug("Attempt to addAnimal form with errors.");
            return badRequest(views.html.animals.render(form));
        }
        AnimalForm formAnimal = form.get();
        Barn barnWhereAnimalLives = barnService.getBarnById(formAnimal.getBarnId());
        Set<Animal> existingAnimals = barnWhereAnimalLives.getAnimals();
        if(existingAnimals.stream().filter(animal -> animal.getName().equals(formAnimal.getName())).count() > 0){
            logger.debug("addAnimal attempted to add non-unique animal name");
            form.reject("Animal name must be unique. Selected Barn already has " + formAnimal.getName());
            return badRequest(views.html.animals.render(form));
        }

        Animal animal = new Animal();
        animal.setName(formAnimal.getName());
        animal.setQuantity(formAnimal.getQuantity());
        animal.setBarn(barnWhereAnimalLives);
        animalService.addAnimal(animal);
        return redirect(routes.Application.index());
    }

}
