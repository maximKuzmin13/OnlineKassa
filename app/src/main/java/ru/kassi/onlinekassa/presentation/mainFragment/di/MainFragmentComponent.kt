package ru.kassi.onlinekassa.presentation.mainFragment.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.kassi.onlinekassa.di.LocalNavigationModule
import ru.kassi.onlinekassa.di.MainNavigationModule
import ru.kassi.onlinekassa.flow.FlowNavigationModule
import ru.kassi.onlinekassa.navigation.FeatureScope
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragment
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowCoordinator
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowFragment

@FeatureScope
@Component(
    modules = [
        ViewModelModule::class,
        CoordinatorModule::class,
        FlowNavigationModule::class,
        LocalNavigationModule::class,
        MainNavigationModule::class,
        ComponentHolderModule::class
              ],
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