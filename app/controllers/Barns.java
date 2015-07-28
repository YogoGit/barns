package controllers;

import models.Barn;
import models.BarnForm;

import services.BarnService;
import views.html.barns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Set;
import java.util.SortedSet;

@org.springframework.stereotype.Controller
public class Barns extends Controller {

    private static final Logger logger = LoggerFactory.getLogger(Barns.class);

    @Autowired
    private BarnService barnService;

    public Result index() {
        return ok(barns.render(Form.form(BarnForm.class)));
    }

    public Result addBarn() {
        Form<BarnForm> form = Form.form(BarnForm.class).bindFromRequest();
        if (form.hasErrors()) {
            logger.debug("addBarn form submitted with errors.");
            return badRequest(barns.render(form));
        }

        BarnForm barnForm = form.get();
        // Verify barn name is unique.
        Set<Barn> existingBarns = barnService.getAllBarns();
        if (existingBarns.stream().filter(barn -> barn.getName().equals(barnForm.getName())).count() > 0) {
            logger.debug("addBarn form submitted with non-unique barn name");
            form.reject("Barn name must be unique.");
            return badRequest(barns.render(form));
        }

        barnService.addBarn(barnForm);
        return redirect(routes.Application.index());
    }

    public Result listBarns() {
        SortedSet<Barn> barns = barnService.getAllBarns();
        logger.trace("listBarns() List of all barns: {}", barns.toString());
        return ok(Json.toJson(barns));
    }

}
