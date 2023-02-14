package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS users(id BIGINT PRIMARY KEY AUTO_INCREMENT, firstname VARCHAR(40), lastname VARCHAR(80), age TINYINT);";

        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(sql)
                    .addEntity(User.class).executeUpdate();

        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS users";

        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(sql)
                    .addEntity(User.class).executeUpdate();

        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);

        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            User user = session.get(User.class, id);

            session.delete(user);

        } finally {
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            List<User> entries = session.createQuery("FROM User").list();
        } finally {
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().getCurrentSession();
        try {
            session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();

        } finally {
            session.getTransaction().commit();
        }
    }
}
