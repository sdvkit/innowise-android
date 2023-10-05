package manager

import model.User

class UserManager {
    private val apiManager: ApiManager<User> = ApiManager()
    private val cacheManager: CacheManager<User> = CacheManager(apiManager)

    fun findUserByKey(key: Int) = cacheManager.getDataFromCacheOrFetch(key)
    fun saveUser(key: Int, user: User) = apiManager.saveData(key, user)
    fun saveAndCacheUser(key: Int, user: User) = cacheManager.saveAndCacheData(key, user)
}