package services;

import models.Barn;
import models.BarnForm;

import play.libs.F.Promise;

import java.util.Set;

public interface BarnService {

    public Integer addBarn(BarnForm bar);

    public Set<Barn> getAllBarns();

    public Promise<Long> getValuation(Set<Barn> barns);

}
