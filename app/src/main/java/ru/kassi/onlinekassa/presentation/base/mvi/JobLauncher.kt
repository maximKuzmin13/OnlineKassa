package ru.kassi.onlinekassa.presentation.base.mvi

import kotlinx.coroutines.CoroutineScope

interface JobLauncher {
    fun addToQueue(block: suspend CoroutineScope.() -> Unit)
    fun exit()
}