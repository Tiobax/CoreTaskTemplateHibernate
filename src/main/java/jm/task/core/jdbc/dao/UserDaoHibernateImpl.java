package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        String sql = "CREATE TABLE IF NOT EXISTS user " +
                "(id BIGINT auto_increment PRIMARY KEY , " +
                "name VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, " +
                "age TINYINT NOT NULL)";
        try {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        }

        Util.closeSession(session);
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        String sql = "DROP TABLE IF EXISTS user";
        try {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        }
        Util.closeSession(session);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println("User с именем – " + name  + " добавлен в базу данных");
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        }
        Util.closeSession(session);
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        try {
            session.beginTransaction();
            User user = session.byId(User.class).load(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        }
        Util.closeSession(session);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        List<User> list = null;
        String sql = "FROM User";
        try {
            session.beginTransaction();
            list = session.createQuery(sql).list();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        }
        Util.closeSession(session);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        String sql = "TRUNCATE TABLE User";
        try {
            session.beginTransaction();
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        }
        Util.closeSession(session);
    }
}
