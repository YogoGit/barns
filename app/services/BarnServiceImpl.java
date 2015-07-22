package services;


import models.Barn;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import play.Logger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Service
@Transactional
public class BarnServiceImpl implements BarnService {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addBarn(Barn bar) {
        em.persist(bar);
    }

    @Override
    public List<Barn> getAllBarns() {
        CriteriaQuery<Barn> c = em.getCriteriaBuilder().createQuery(Barn.class);
        c.from(Barn.class);
        return em.createQuery(c).getResultList();
    }

}