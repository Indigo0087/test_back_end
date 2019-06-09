package DAO

import org.hibernate.Session
import utils.HibernateUtils.buildSession

class DefaultDaoImpl<T>{// : DefaultDao {

    private var session: Session = buildSession<Any>().openSession()

    fun create(o: Any) {
        session = buildSession<Any>().openSession()
        session.beginTransaction()
        session.save(o)
        session.transaction.commit()
        session.close()
    }

    fun update(o: Any) {
        session = buildSession<Any>().openSession()
        session.beginTransaction()
        session.update(o)
        session.transaction.commit()
        session.close()
    }

    fun delete(id: Long) {
        session = buildSession<Any>().openSession()
        session.beginTransaction()
        session.delete(session.load(Any::class.java, id))
        session.transaction.commit()
        session.close()
    }

    fun getAll(hql : String): List<Any> {
        session = buildSession<Any>().openSession()
        val list = session.createQuery(hql).list() as List<Any>
        session.close()
        return list
    }

    fun getById(id: Long, clazz:Class<T>): T {
        session = buildSession<Any>().openSession()
        session.beginTransaction()
        val o = session.load(clazz.simpleName, id)
        session.transaction.commit()
        session.close()
        return o as T
    }

    fun getByLogin(login: String, clazz:Class<T>): T {
        session = buildSession<Any>().openSession()
        session.beginTransaction()
        val hql = "select a from ${clazz.simpleName} a where login='$login'"
        val o= session.createQuery(hql).list().first()
        session.transaction.commit()
        session.close()
        return o as T
    }
}