package com.bases.spboot.estudio.controllers;

import com.bases.spboot.estudio.dao.activity.ActivityDao;
import com.bases.spboot.estudio.dao.activity.Times.ActivityTimeDao;
import com.bases.spboot.estudio.models.Activity;
import com.bases.spboot.estudio.models.ActivityTime;
import com.bases.spboot.estudio.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/activity")
public class ActivityController {

    @Autowired
    ActivityDao activityDao;

    @Autowired
    ActivityTimeDao activityTimeDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(method = RequestMethod.POST)
    Activity register(@RequestHeader(value="Authorization") String token, @RequestBody Activity activity){
        String userId = jwtUtil.getKey(token);

        if(userId != null){
            activity.setUserId(Long.valueOf(userId));
            return activityDao.registrar(activity);
        }
        return null;
    }

    @RequestMapping(value = "/times", method = RequestMethod.POST)
    ActivityTime registertTimes(@RequestHeader(value="Authorization") String token, @RequestBody ActivityTime activityTime){
        String userId = jwtUtil.getKey(token);

        if(userId != null){
            return activityTimeDao.registrar(activityTime);
        }
        return null;
    }

    @RequestMapping(method = RequestMethod.GET)
    List<Activity> getAllActivities(){
        return activityDao.getActivities();
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    List<Activity> getActivityByUser(@RequestHeader(value="Authorization") String token) {
        String userId = jwtUtil.getKey(token);

        if(userId != null){
            return activityDao.getActivityByUser(Long.valueOf(userId));
        }
        return  new ArrayList<Activity>();
    }

}
