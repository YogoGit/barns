package models;

import play.data.validation.Constraints.Required;

public class AnimalForm {

    @Required(message = "You must enter an animal name.")
    private String name;

    @Required(message = "You must specify a barn first.")
    private Integer barnId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBarnId() {
        return barnId;
    }

    public void setBarnId(Integer barnId) {
        this.barnId = barnId;
    }

}
