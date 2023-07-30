package ru.kassi.onlinekassa.presentation.kassiFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class KassiViewModel @Inject constructor(
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<KassiState, KassiIntent>(KassiState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: KassiIntent): KassiState {
        return when (intent) {
            KassiIntent.Loading -> currentState
            KassiIntent.Start -> currentState
        }
    }

}