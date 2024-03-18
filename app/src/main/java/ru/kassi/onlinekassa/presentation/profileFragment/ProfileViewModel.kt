package ru.kassi.onlinekassa.presentation.profileFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.api.profile.Profile
import ru.kassi.onlinekassa.domain.api.profile.ProfileRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.profileFragment.coordinator.ProfileCoordinator
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileCoordinator: ProfileCoordinator,
    private val profileRepository: ProfileRepository,
    private val resources: ResourceManager,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : MviViewModel<EmptyNavArgs, ProfileState, ProfileIntent>(ProfileState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    init {
        getProfileData()
    }

    override suspend fun reduceState(intent: ProfileIntent){
        return when (intent) {
            ProfileIntent.Loading -> {}
            ProfileIntent.Start -> {}
            ProfileIntent.Back -> {
                profileCoordinator.goToMain()
            }

            ProfileIntent.Logout -> {
                profileRepository.logout()
                profileCoordinator.goToAuth()
            }
        }
    }

    fun getProfileData() {
        viewModelScope.launch(
            SupervisorJob() + 
                    dispatcher +
                    CoroutineExceptionHandler { _, _ ->  }) {
            val profile = profileRepository.getProfile()?.response
            val list = mutableListOf<ru.kassi.onlinekassa.data.Profile>()
            list.add(ru.kassi.onlinekassa.data.Profile(
                Pair("ИНН", profile?.inn.toString())
            ))
            list.add(ru.kassi.onlinekassa.data.Profile(
                Pair("ФИО", profile?.fio)
            ))
            list.add(ru.kassi.onlinekassa.data.Profile(
                Pair("Почта", profile?.mail)
            ))
            _state.value = currentState.copy(
                profile = list
            )
        }
    }
}