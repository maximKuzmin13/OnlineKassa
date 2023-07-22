package ru.kassi.onlinekassa.presentation.mainFragment.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.kassi.onlinekassa.di.MainFragmentDeps
import ru.kassi.onlinekassa.flow.FlowNavigationModule
import ru.kassi.onlinekassa.navigation.FeatureScope
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowFragment
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragment

@FeatureScope
@Component(
    modules = [ViewModelModule::class, CoordinatorModule::class, FlowNavigationModule::class, ComponentHolderModule::class],
    dependencies = [MainFragmentDeps::class]
)
interface MainFragmentComponent {

    fun inject(fragment: MainFragment)
    fun inject(fragment: MainFlowFragment)
    fun inject(coordinator: MainFlowCoordinator)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, deps: MainFragmentDeps): MainFragmentComponent
    }
}