package manager

import log.Log

class CacheManager<T> (private val apiManager: ApiManager<T>) {
    private val cache: MutableMap<Int, T> = mutableMapOf()

    fun getDataFromCacheOrFetch(key: Int): T {
        val cachedData: T? = cache[key]

        if (cachedData != null) {
            Log.info("There's a data#$cachedData in the cache")
            return cachedData
        }

        val data = apiManager.getData(key)
        cache[key] = data
        Log.info("Data#$data with Key#$key has been saved to the cache")
        return data
    }

    fun saveAndCacheData(key: Int, data: T) {
        val savedData = apiManager.saveData(key, data)
        cache[key] = savedData
    }
}