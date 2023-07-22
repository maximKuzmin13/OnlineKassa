package ru.kassi.onlinekassa.presentation.mainFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import ru.kassi.onlinekassa.di.ViewModelAssistedFactory

class MainFragmentViewModel @AssistedInject constructor(
    @Assisted arg0: SavedStateHandle,
) : ViewModel() {

    init {
        print("ssss")
    }


    @AssistedInject.Factory
    interface Factory : ViewModelAssistedFactory<MainFragmentViewModel>
}
