package ru.kassi.onlinekassa.presentation.mainFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ru.kassi.onlinekassa.di.ViewModelAssistedFactory
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowCoordinator

class MainFragmentViewModel @AssistedInject constructor(
    @Assisted arg0: SavedStateHandle,
    private val coordinator: MainFlowCoordinator
) : ViewModel() {

    init {
        print("ssss")
    }

    fun goToLogin() {
        coordinator.goToLogin()
    }

    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainFragmentViewModel>
}
