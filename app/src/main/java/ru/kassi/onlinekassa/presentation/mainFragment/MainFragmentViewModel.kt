package ru.kassi.onlinekassa.presentation.mainFragment

import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val coordinator: MainFragmentCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<MainFragmentState, MainFragmentIntent>(MainFragmentState()) {

    init {
        Log.d("tag", "main")
    }

    fun goToProfile() {
        coordinator.goToProfile()
    }

    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: MainFragmentIntent): MainFragmentState {
        return when (intent) {
            MainFragmentIntent.Loading -> currentState
            MainFragmentIntent.Start -> currentState
            is MainFragmentIntent.PointClick -> {
                coordinator.goToKassa(intent.pointId)
                currentState.copy(onClick = intent.pointId)
            }
        }
    }

}
