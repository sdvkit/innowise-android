package manager

import exception.DataNotFoundException

class ApiManager<T> {
    private val dataSource: MutableMap<Int, T> = mutableMapOf()

    fun saveData(key: Int, data: T): T {
        dataSource[key] = data
        return data
    }

    fun getData(key: Int) = dataSource[key] ?: throw DataNotFoundException("Data with Key#$key not found.")
}