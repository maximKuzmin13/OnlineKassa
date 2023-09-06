package ru.kassi.onlinekassa.presentation.profileFragment

import androidx.lifecycle.SavedStateHandle
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.profileFragment.coordinator.ProfileCoordinator
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileCoordinator: ProfileCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<EmptyNavArgs, ProfileState, ProfileIntent>(ProfileState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: ProfileIntent){
        return when (intent) {
            ProfileIntent.Loading -> {}
            ProfileIntent.Start -> {}
            ProfileIntent.Back -> {
                profileCoordinator.goToMain()
            }
        }
    }

}