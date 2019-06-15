package DAO

open class Factory<T> {

    private var defaultDAO: UserDaoImpl<T>? = null

    fun getUserDAO(): UserDaoImpl<T> {
        if (defaultDAO == null) {
            defaultDAO = UserDaoImpl()
        }
        return defaultDAO!!
    }

}