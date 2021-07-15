package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        sessionFactory = Util.getSessionFactory();
    }

    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user " +
                    "(id BIGINT auto_increment PRIMARY KEY , " +
                    "name VARCHAR(45) NOT NULL, " +
                    "lastName VARCHAR(45) NOT NULL, " +
                    "age TINYINT NOT NULL)").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                }

            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                }

            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
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
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                }

            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("DELETE User WHERE id = :id")
                    .setParameter("id", id);
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                }

            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> list = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            list = session.createQuery("FROM User").list();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                }

            }
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE User").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception ex1) {
            try {
                session.getTransaction().rollback();
            } catch (Exception ex2) {
                ex2.printStackTrace();
            }
            ex1.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (Exception ex3) {
                    ex3.printStackTrace();
                }

            }
        }
    }
}
