package ru.kassi.onlinekassa.presentation.profileFragment

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<ProfileState, ProfileIntent>(ProfileState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: ProfileIntent): ProfileState {
        return when (intent) {
            ProfileIntent.Loading -> currentState
            ProfileIntent.Start -> currentState
        }
    }

}