package com.bases.spboot.estudio.dao.user;


import com.bases.spboot.estudio.models.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers();

    User getOneUser(Long id);

    void eliminar(Long id);

    User registrar(User usuario);

    User obtenerUsuarioPorCredenciales(User usuario);

}
