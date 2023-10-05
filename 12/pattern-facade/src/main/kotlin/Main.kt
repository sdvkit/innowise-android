import manager.UserManager
import model.User

fun main() {
    val userManager = UserManager()

    userManager.saveUser(1, User("Ivanov I.I."))
    val ivan = userManager.findUserByKey(1)
    println(ivan)

    userManager.saveAndCacheUser(2, User("Petrov P.P."))
    val petr = userManager.findUserByKey(2)
    println(petr)
}