package ru.kassi.onlinekassa.di

import androidx.lifecycle.ViewModel
import com.squareup.inject.assisted.dagger2.AssistedModule
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.kassi.onlinekassa.presentation.singleActivity.MainViewModel

@Module(includes = [ViewModelAssistedFactoriesModule::class])
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun mainActivityVMFactory(factory: MainViewModel.Factory): ViewModelAssistedFactory<out ViewModel>
}

@AssistedModule
@Module(includes = [AssistedInject_ViewModelAssistedFactoriesModule::class])
abstract class ViewModelAssistedFactoriesModule
