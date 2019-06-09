package utils

import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration

object HibernateUtils {

    inline fun <reified T> buildSession(): SessionFactory {
        return Configuration()
            .configure()
            .addAnnotatedClass(T::class.java)
            .buildSessionFactory()!!

    }

}