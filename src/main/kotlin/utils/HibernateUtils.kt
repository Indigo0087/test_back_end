package utils

import org.hibernate.SessionFactory
import org.hibernate.boot.MetadataSources
import org.hibernate.boot.registry.StandardServiceRegistryBuilder
import org.hibernate.cfg.Configuration

object HibernateUtils {

    val sessionFactory: SessionFactory

    init {
        val configuration = Configuration()
        configuration.configure("hibernate.cfg.xml")
        val serviceRegester = StandardServiceRegistryBuilder()
            .configure("hibernate.cfg.xml")
            .configure()
        val meta = MetadataSources(serviceRegester.build())
            .metadataBuilder.build()

        sessionFactory = meta.sessionFactoryBuilder.build()
    }

}