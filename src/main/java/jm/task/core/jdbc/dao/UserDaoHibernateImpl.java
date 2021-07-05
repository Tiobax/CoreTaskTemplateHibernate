package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSession();
        String sql = "CREATE TABLE IF NOT EXISTS user " +
                "(id INT auto_increment PRIMARY KEY , " +
                "name VARCHAR(45) NOT NULL, " +
                "lastName VARCHAR(45) NOT NULL, " +
                "age INT NOT NULL)";
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        Util.closeSession(session);
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSession();
        String sql = "DROP TABLE IF EXISTS user";
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        Util.closeSession(session);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSession();
        session.beginTransaction();
        session.save(new User(name, lastName, age));
        session.getTransaction().commit();
        System.out.println("User с именем – " + name  + " добавлен в базу данных");
        Util.closeSession(session);
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        session.beginTransaction();
        Object user = session.get(User.class, id);
        session.getTransaction().commit();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
        Util.closeSession(session);
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSession();
        String sql = "SELECT * FROM user";
        session.beginTransaction();
        List<User> list = session.createSQLQuery(sql).addEntity(User.class).list();
        Util.closeSession(session);
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        String sql = "DELETE FROM user";
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        Util.closeSession(session);
    }
}
