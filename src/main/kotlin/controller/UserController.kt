package controller

import DAO.DefaultDaoImpl
import DAO.Factory
import io.javalin.Context
import model.User
import org.slf4j.LoggerFactory
import utils.Constants
import java.sql.SQLException

object UserController {

    lateinit var dao : DefaultDaoImpl<User>
    val logger = LoggerFactory.getLogger(this::class.java)

    init {
        try {
            dao = Factory<User>().getUserDAO()
        } catch (e : SQLException){
            logger.error("Err creating DAO " + e.message)
        }
    }

    fun create(ctx: Context) {
        val user = ctx.bodyAsClass(User::class.java)
        try {
            dao.create(user)
            ctx.status(Constants.CREATED_201)
        } catch (e : SQLException){
            logger.error("Err saving")
            e.printStackTrace()
            ctx.status(Constants.INTERNAL_SERVER_ERROR_500)
        }
    }

    fun delete(ctx: Context, resourceId: String) {
        val userId = resourceId.toLong()
        try {
            dao.delete(userId)
        }catch (e : SQLException){
            logger.error("Err deleting")
            ctx.status(Constants.INTERNAL_SERVER_ERROR_500)
        }
    }

    fun getAll(ctx: Context) {
        try {
            ctx.json(dao.getAll("select a from ${User::class.java.simpleName} a"))
        }catch (e : SQLException){
            logger.error("Err getting all")
            ctx.status(Constants.INTERNAL_SERVER_ERROR_500)
        }
    }

    fun getOne(ctx: Context, resourceId: String) {
        val userId = resourceId.toLong()
        logger.debug(resourceId)
        try {
            val user = dao.getById(userId, User::class.java)
            ctx.json(user)
        } catch (e : SQLException){
            logger.error("Err getting one")
            ctx.status(Constants.INTERNAL_SERVER_ERROR_500)
        }
    }

    fun update(ctx: Context, resourceId: String) {
        val userId = resourceId.toLong()
        val user = ctx.bodyAsClass(User::class.java)
        user.id = userId
        try {
            dao.update(user)
        }catch (e : SQLException){
            logger.error("bookId")
            ctx.status(Constants.INTERNAL_SERVER_ERROR_500)
        }
    }

    fun getByLogin(ctx: Context){
        val userId = ctx.header("login")
        try {
            val user = dao.getByLogin(userId!!, User::class.java)
            ctx.json(user)
        } catch (e : SQLException){
            logger.error("Err getting one")
            ctx.status(Constants.INTERNAL_SERVER_ERROR_500)
        }
    }


}