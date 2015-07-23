package services;

import models.Barn;
import models.BarnForm;

import java.util.List;
import java.util.Set;

public interface BarnService {

    public void addBarn(BarnForm bar);
    public Set<Barn> getAllBarns();

}