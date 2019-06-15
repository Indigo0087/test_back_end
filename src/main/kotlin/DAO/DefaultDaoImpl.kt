package DAO

import model.User
import org.hibernate.Session
import utils.HibernateUtils.sessionFactory

class UserDaoImpl<User>{

    private var session: Session = sessionFactory.openSession()

    fun create(o: User) {
        session = sessionFactory.openSession()
        session.beginTransaction()
        session.save(o)
        session.transaction.commit()
        session.close()
    }

    fun update(o: User) {
        session = sessionFactory.openSession()
        session.beginTransaction()
        session.update(o)
        session.transaction.commit()
        session.close()
    }

    fun delete(id: Long) {
        session = sessionFactory.openSession()
        session.beginTransaction()
        session.delete(session.load("User", id))
        session.transaction.commit()
        session.close()
    }

    fun getAll(hql : String): List<User> {
        session = sessionFactory.openSession()
        val list = session.createQuery(hql).list() as List<User>
        session.close()
        return list
    }

    fun getById(id: Long): User {
        session = sessionFactory.openSession()
        session.beginTransaction()
        val o = session.load("User", id) as User
        session.transaction.commit()
        session.close()
        return o
    }

    fun getByLogin(login: String, clazz:Class<User>): User {
        session = sessionFactory.openSession()
        session.beginTransaction()
        val hql = "select a from User a where login='$login'"
        val o= session.createQuery(hql).list().first() as User
        session.transaction.commit()
        session.close()
        return o
    }
}