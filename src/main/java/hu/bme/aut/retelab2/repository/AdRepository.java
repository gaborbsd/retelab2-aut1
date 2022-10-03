package hu.bme.aut.retelab2.repository;


import hu.bme.aut.retelab2.domain.Ad;
import org.springframework.dao.NonTransientDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Repository
public class AdRepository {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Ad save(Ad ad) { return em.merge(ad); }

    public List<Ad> getByRange(Integer min, Integer max) {
        String query = String.format("SELECT a FROM Ad a WHERE a.price >= %d AND a.price <= %d", min, max );
        return em.createQuery(query, Ad.class).getResultList();
    }

    public Ad findById(long id) {
        return em.find(Ad.class, id);
    }

    @Transactional
    public Ad update(Ad ad) {
        Ad resultAd = findById(ad.getId());
        if (resultAd == null) throw new EntityNotFoundException();
        else if (Objects.equals(resultAd.getSecretKey(), ad.getSecretKey())) {
            return em.merge(ad);
        } else throw new NonTransientDataAccessException("You have entered an invalid secret key"){};
    }

    public List<Ad> findByTag(String tag) {
        List<Ad> resultList = em.createQuery("SELECT a FROM Ad a JOIN a.tags t WHERE t = LOWER(?1)", Ad.class).setParameter(1, tag).getResultList();
        for (Ad ad : resultList) ad.setSecretKey(null);
        return resultList;
    }
}
