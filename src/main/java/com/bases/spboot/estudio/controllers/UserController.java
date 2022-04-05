package com.bases.spboot.estudio.controllers;

import com.bases.spboot.estudio.dao.user.UserDao;
import com.bases.spboot.estudio.models.User;
import com.bases.spboot.estudio.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUsers() {
        //if (!validarToken(token)) { return null; }
        return userDao.getUsers();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<User> getUserById(@PathVariable Long id) {
        //if (!validarToken(token)) { return null; }
        return userDao.getUsers();
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public User registerUser(@RequestBody User user) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1, 1024, 1, user.getPassword());
        user.setPassword(hash);

        Date dt = new Date();

        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String currentTime = sdf.format(dt);

        user.setCreatAt(currentTime);

        return userDao.registrar(user);
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

}
