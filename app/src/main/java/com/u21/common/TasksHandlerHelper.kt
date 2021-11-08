package com.u21.common

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner


class TasksHandlerHelper : DefaultLifecycleObserver {
    private var handler: Handler? = null

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        handler = Handler(Looper.getMainLooper())
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        handler?.removeCallbacksAndMessages(null)
        handler = null
    }

    fun runTaskWithDelay(task: () -> Unit, delay: Long) {
        handler?.postDelayed(task, delay)
    }
}