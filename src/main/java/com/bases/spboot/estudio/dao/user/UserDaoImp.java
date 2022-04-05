package com.bases.spboot.estudio.dao.user;

import com.bases.spboot.estudio.models.User;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<User> getUsers() {
        String query = "FROM User";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public User getOneUser(Long id) {
        String query = "FROM User WHERE id = :id";
        List<User> result = entityManager.createQuery(query)
                .setParameter("id", id)
                .getResultList();

        if (result.isEmpty()) {
            return null;
        }

        return result.get(0);
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public User registrar(User user) {
        return entityManager.merge(user);
    }

    @Override
    public User obtenerUsuarioPorCredenciales(User user) {
        String query = "FROM User WHERE user_name = :userName";
        List<User> lista = entityManager.createQuery(query)
                .setParameter("userName", user.getUserName())
                .getResultList();

        if (lista.isEmpty()) {
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon2.verify(passwordHashed, user.getPassword())) {
            return lista.get(0);
        }
        return null;
    }
}
