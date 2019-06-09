import controller.UserController
import io.javalin.Context
import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder.*
import io.javalin.json.JavalinJackson
import io.javalin.security.Role
import io.javalin.security.SecurityUtil.roles
import utils.Constants
import utils.JacksonUtils

fun main() {
    val app = Javalin.create()
        .enableDebugLogging()
        .port(Constants.PORT)

    JavalinJackson.configure(JacksonUtils.mapper)

    app.accessManager { handler, ctx, permittedRoles ->
        val role = getUserRole(ctx)
        if (permittedRoles.contains(role)) {
            handler.handle(ctx)
        } else ctx.status(401).result("Unauthorized")    }

    app.routes {
        get("/users", { ctx -> UserController.getAll(ctx) }, roles(R.N))
        get("/users:id", { ctx -> println(ctx.pathParam("id"))
            UserController.getOne(ctx, ctx.pathParam("id")) }, roles(R.N))
        get("/user", { ctx -> UserController.getByLogin(ctx) }, roles(R.N))
        post("/signup", { ctx -> UserController.create(ctx) }, roles(R.N))
        delete("delete:id", { ctx -> UserController.delete(ctx, ctx.pathParam("id")) } , roles(R.N))
        patch("update", { ctx -> UserController.update(ctx, ctx.pathParam("id")) }, roles(R.N))
    }.start()

}

fun getUserRole(ctx: Context): Role? {
    return R.N
}

enum class R : Role{
    N
}


