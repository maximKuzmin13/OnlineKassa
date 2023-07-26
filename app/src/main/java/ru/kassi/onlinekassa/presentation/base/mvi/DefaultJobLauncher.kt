package ru.kassi.onlinekassa.presentation.base.mvi

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

class DefaultJobLauncher(private val safeScope: CoroutineScope) : JobLauncher {

    private val queue = Channel<Job>(Channel.UNLIMITED)

    init {
        safeScope.launch { queue.consumeEach { it.join() } }
    }

    override fun addToQueue(block: suspend CoroutineScope.() -> Unit) {
        queue.trySend(safeScope.launch(safeScope.coroutineContext, CoroutineStart.LAZY, block))
    }

    override fun exit() {
        queue.cancel()
        safeScope.cancel()
    }
}