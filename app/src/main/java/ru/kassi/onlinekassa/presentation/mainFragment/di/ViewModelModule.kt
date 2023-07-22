package ru.kassi.onlinekassa.presentation.mainFragment.di

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.kassi.onlinekassa.di.ViewModelAssistedFactory
import ru.kassi.onlinekassa.di.ViewModelKey
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragmentViewModel

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainFragmentViewModel::class)
    fun bindMainFragmentVMFactory(factory: MainFragmentViewModel.Factory): ViewModelAssistedFactory<out ViewModel>

}
