package services;


import models.Barn;
import models.BarnForm;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BarnServiceImpl implements BarnService {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addBarn(BarnForm barn) {
        Barn b = new Barn();
        b.setName(barn.getName());
        em.persist(b);
    }

    @Override
    public Set<Barn> getAllBarns() {
        CriteriaQuery<Barn> c = em.getCriteriaBuilder().createQuery(Barn.class);
        c.from(Barn.class);
        return new HashSet<Barn>(em.createQuery(c).getResultList());
    }

}