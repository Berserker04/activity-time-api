package com.bases.spboot.estudio.controllers;

import com.bases.spboot.estudio.dao.user.UserDao;
import com.bases.spboot.estudio.models.User;
import com.bases.spboot.estudio.utils.JWTUtil;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/auth")
public class AuthController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @Value("${security.jwt.secret}")
    private String key;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {

        User userLogin = userDao.obtenerUsuarioPorCredenciales(user);
        if (userLogin != null) {
            String tokenJwt = jwtUtil.create(String.valueOf(userLogin.getId().toString()), userLogin.getUserName());
            return tokenJwt;
        }
        return "FAIL";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser(@RequestHeader(value="Authorization") String token) {
        String userId = jwtUtil.getKey(token);

        if(userId != null){
            return userDao.getOneUser(Long.valueOf(userId));
        }

        return  null;
    }

}
