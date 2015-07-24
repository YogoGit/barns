package models;

import play.data.validation.Constraints.Required;

/**
 * Non-entity pojo for adding a Barn.
 *
 * @author rlewan
 *
 */
public class BarnForm {

    @Required(message = "Barn must have a name.")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
