package services;


import models.Animal;
import models.Barn;
import models.BarnForm;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import play.libs.F.Promise;
import play.libs.Json;
import play.libs.ws.WS;
import play.libs.ws.WSResponse;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

@Service
@Transactional
public class BarnServiceImpl implements BarnService {

    private static final Logger logger = LoggerFactory.getLogger(BarnServiceImpl.class);
    private static final String NODE_SERVICE_URL = "http://localhost:9055";

    @PersistenceContext
    EntityManager em;

    @Override
    public Integer addBarn(BarnForm barn){
        Barn b = new Barn();
        b.setName(barn.getName());
        b.setAnimals(new HashSet<Animal>());
        em.persist(b);
        logger.debug("Barn with name {} and barnId {} persisted to db.", barn.getName(), b.getBarnId());
        return b.getBarnId();
    }

    @Override
    public Set<Barn> getAllBarns() {
        CriteriaQuery<Barn> c = em.getCriteriaBuilder().createQuery(Barn.class);
        c.from(Barn.class);
        List<Barn> barns = em.createQuery(c).getResultList();
//        if(barns == null || barns.isEmpty()){
//            return new HashSet<Barn>();
//        }
        return new HashSet<Barn>(barns);
    }

    @Override
    public Promise<Long> getValuation(Set<Barn> barns) {
        if(barns == null || barns.size() == 0){
            return Promise.pure(0l);
        }
        JsonNode barnsJson = Json.toJson(barns);
        Promise<Long> result = WS.url(NODE_SERVICE_URL).post(barnsJson).map(response -> {
            return response.asJson().get("value").asLong();
        });

        return result;
    }

}