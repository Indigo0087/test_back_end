package DAO

open class Factory<T> {

    private var defaultDAO: DefaultDaoImpl<T>? = null

    fun getUserDAO(): DefaultDaoImpl<T> {
        if (defaultDAO == null) {
            defaultDAO = DefaultDaoImpl()
        }
        return defaultDAO!!
    }

}