package com.sdv.kit.application.util

import android.util.Log

class LogUtil(private val tag: String) {
    fun logMethod() {
        val stackTrace = Thread.currentThread().stackTrace

        if (stackTrace.size >= 3) {
            val caller = stackTrace[3]
            val methodName = caller.methodName

            Log.d(tag, "Inside $methodName(...) -> $caller")
        }
    }
}