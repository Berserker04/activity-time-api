package com.bases.spboot.estudio.dao.activity.Times;

import com.bases.spboot.estudio.models.ActivityTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class ActivityTimeDaoImp implements ActivityTimeDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public ActivityTime registrar(ActivityTime activity) {
        return entityManager.merge(activity);
    }
}
