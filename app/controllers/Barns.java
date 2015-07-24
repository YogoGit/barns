package controllers;

import models.Barn;
import models.BarnForm;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import play.data.Form;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Controller;


import services.BarnService;
import views.html.barns;

@org.springframework.stereotype.Controller
public class Barns {

    private static final Logger logger = LoggerFactory.getLogger(Barns.class);

    @Autowired
    private BarnService barnService;

    public Result index() {
        return play.mvc.Controller.ok(barns.render(Form.form(BarnForm.class)));
    }

    public Result addBarn() {
        Form<BarnForm> form = Form.form(BarnForm.class).bindFromRequest();
        if (form.hasErrors()) {
            return play.mvc.Controller.badRequest(barns.render(form));
        }

        BarnForm barn = form.get();
        barnService.addBarn(barn);
        return play.mvc.Controller.redirect(controllers.routes.Application.index());
    }

    public Result listBarns() {
        Set<Barn> barns = barnService.getAllBarns();
        logger.info("listBarns() List of all barns: " + barns.toString());
        return play.mvc.Controller.ok(Json.toJson(barns));
    }

}