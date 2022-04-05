package com.bases.spboot.estudio.dao.activity;

import com.bases.spboot.estudio.models.Activity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class ActivityDaoImp implements ActivityDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Activity> getActivities() {
        String query = "FROM Activity";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Activity> getActivityByUser(Long id) {
        String query = "FROM Activity WHERE userId = :id";
        return entityManager.createQuery(query)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public Activity registrar(Activity activity) {
        return entityManager.merge(activity);
    }
}
