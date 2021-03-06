package services;

import models.Barn;
import models.BarnForm;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;

import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

/**
 * BarnServiceImpl does some basic fetch & create for Barn Entity and also posts Barns data as JSON to a node.js http server to
 * fetch the barns' valuation.
 *
 * @author rlewan
 *
 */
@Service
public class BarnServiceImpl implements BarnService {

    private static final Logger logger = LoggerFactory.getLogger(BarnServiceImpl.class);
    private static final String NODE_SERVICE_URL = "http://localhost:9055";

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Barn addBarn(BarnForm barn) {
        if(barn == null) {
            throw new IllegalArgumentException();
        }

        Barn b = new Barn();
        b.setName(barn.getName());
        b = em.merge(b);
        logger.debug("Barn with name {} and barnId {} persisted to db.", barn.getName(), b.getBarnId());
        return b;
    }

    @Override
    @Transactional
    public Barn save(Barn barn) {
        return em.merge(barn);
    }

    @Override
    public Barn getBarnById(Integer id) {
        return em.find(Barn.class, id);
    }

    @Override
    public SortedSet<Barn> getAllBarns() {
        CriteriaQuery<Barn> c = em.getCriteriaBuilder().createQuery(Barn.class);
        c.from(Barn.class);
        List<Barn> barns = em.createQuery(c).getResultList();
        SortedSet<Barn> barnsSet = new TreeSet<Barn>(barns);
        return barnsSet;
    }

    /**
     * Asynchronous call to http server that runs an algorithm on the barns to calculate their overall valuation.
     */
    @Override
    public Promise<Long> getValuation(Set<Barn> barns) {
        if (barns == null || barns.size() == 0) {
            return Promise.pure(0L);
        }
        JsonNode barnsJson = Json.toJson(barns);
        Promise<Long> result = WS.url(NODE_SERVICE_URL).post(barnsJson).map(response -> {
            return response.asJson().get("value").asLong();
        });

        return result;
    }

}
