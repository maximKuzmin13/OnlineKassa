package ru.kassi.onlinekassa.presentation.loginFragment.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import ru.kassi.onlinekassa.flow.FlowNavigationModule
import ru.kassi.onlinekassa.navigation.FeatureScope
import ru.kassi.onlinekassa.presentation.loginFragment.LoginFragment
import ru.kassi.onlinekassa.presentation.loginFragment.flow.LoginFlowCoordinator
import ru.kassi.onlinekassa.presentation.loginFragment.flow.LoginFlowFragment

@FeatureScope
@Component(
    modules = [CoordinatorModule::class, FlowNavigationModule::class, ComponentHolderModule::class],
    dependencies = [LoginFragmentDeps::class]
)
interface LoginFragmentComponent {

    fun inject(fragment: LoginFragment)
    fun inject(fragment: LoginFlowFragment)
    fun inject(coordinator: LoginFlowCoordinator)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context, deps: LoginFragmentDeps): LoginFragmentComponent
    }
}