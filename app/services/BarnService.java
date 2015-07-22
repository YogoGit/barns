package services;

import models.Barn;

import java.util.List;

public interface BarnService {

    public void addBarn(Barn bar);
    public List<Barn> getAllBarns();

}