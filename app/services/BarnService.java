package services;

import models.Barn;
import models.BarnForm;

import play.libs.F.Promise;

import java.util.Set;

public interface BarnService {

    public Barn getBarnById(Integer id);

    public Barn addBarn(BarnForm bar);

    public void save(Barn barn);

    public Set<Barn> getAllBarns();

    public Promise<Long> getValuation(Set<Barn> barns);

}
