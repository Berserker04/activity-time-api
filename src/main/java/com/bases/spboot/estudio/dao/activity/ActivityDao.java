package com.bases.spboot.estudio.dao.activity;

import com.bases.spboot.estudio.models.Activity;
import com.bases.spboot.estudio.models.User;

import java.util.List;

public interface ActivityDao {

    List<Activity> getActivities();

    List<Activity> getActivityByUser(Long id);

    Activity registrar(Activity activity);

}
