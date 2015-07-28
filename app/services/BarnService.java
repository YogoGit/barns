package services;

import models.Barn;
import models.BarnForm;

import play.libs.F.Promise;

import java.util.Set;
import java.util.SortedSet;

public interface BarnService {

    /**
     * Gets a Barn instance by its primary key identifier.
     *
     * @param id Primary key of barn.
     * @return Barn instance, if any, for the given key.
     */
    Barn getBarnById(Integer id);

    /**
     * Persists a Barn to the db based on BarnForm properties.
     *
     * @param barnForm An encapsulation of Barn properties in an adapter object that does not contain a key.
     * @return Barn instance resulting from the persist of barn data.
     */
    Barn addBarn(BarnForm barnForm);

    /**
     * Persists a barn.
     *
     * @param barn the barn to persist
     * @return Barn instance with auto generated identifier value.
     */
    Barn save(Barn barn);

    /**
     * Fetches all barns from the DB.
     *
     * @return SortedSet of Barns
     */
    SortedSet<Barn> getAllBarns();

    /**
     * Makes a valuation of a set of barns by calling an external service.
     *
     * @param barns Set of barns to evaluate/appraise.
     * @return A Promise of Long representing the appraisal of the set of barns.
     */
    Promise<Long> getValuation(Set<Barn> barns);

}
