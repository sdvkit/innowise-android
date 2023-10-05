package com.sdv.kit.taskard.util

import android.content.Context
import android.content.SharedPreferences
import com.sdv.kit.taskard.db.DatabaseClient
import com.sdv.kit.taskard.db.dao.UserDao
import com.sdv.kit.taskard.entity.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AuthStorageUtil(context: Context) {
    private val sharedPref: SharedPreferences by lazy {
        context.getSharedPreferences(AUTH_SHARED_PREF_NAME, Context.MODE_PRIVATE)
    }

    private val userDao: UserDao by lazy {
        DatabaseClient.instance(context).userDao()
    }

    fun isUserIdSaved(): Boolean = sharedPref.getString(
        USER_ID_SHARED_PREF_KEY, EMPTY_USER_ID
    ) != EMPTY_USER_ID

    fun getUserId(): String =
        sharedPref.getString(USER_ID_SHARED_PREF_KEY, EMPTY_USER_ID)!!

    fun removeUserId() {
        sharedPref.edit().apply {
            remove(USER_ID_SHARED_PREF_KEY)
            apply()
        }
    }

    suspend fun saveUser(user: User) = withContext(Dispatchers.IO) {
        userDao.save(user)
        saveUserId(user.id)
    }

    suspend fun getSavedUser(): User? = withContext(Dispatchers.IO) {
        userDao.findById(getUserId())
    }

    private fun saveUserId(userId: String) {
        sharedPref.edit().apply {
            putString(USER_ID_SHARED_PREF_KEY, userId)
            apply()
        }
    }

    companion object {
        private const val AUTH_SHARED_PREF_NAME = "auth_shared_pref"
        private const val USER_ID_SHARED_PREF_KEY = "user"
        private const val EMPTY_USER_ID = "empty_user_id"
    }
}