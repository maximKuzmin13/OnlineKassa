package ru.kassi.onlinekassa.presentation.singleActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ru.kassi.onlinekassa.di.ViewModelAssistedFactory
import ru.kassi.onlinekassa.navigation.Screens
import ru.kassi.onlinekassa.navigation.StartUpCoordinatorImpl

class MainViewModel @AssistedInject constructor(
    @Assisted arg0: SavedStateHandle,
    private val startUpCoordinatorImpl: StartUpCoordinatorImpl
) : ViewModel() {

    fun goToScreen(screen: FragmentScreen) {
        startUpCoordinatorImpl.start(screen)
    }


    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainViewModel>
}
