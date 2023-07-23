package ru.kassi.onlinekassa.presentation.loginFragment.coordinator

import ru.kassi.onlinekassa.di.ComponentHolder
import ru.kassi.onlinekassa.presentation.loginFragment.di.LoginFragmentComponent
import ru.kassi.onlinekassa.presentation.loginFragment.flow.LoginFlowCoordinator
import javax.inject.Inject

class LoginFragmentCoordinatorImpl @Inject constructor(
    private val loginFlowCoordinator: LoginFlowCoordinator,
    private val componentHolder: ComponentHolder<LoginFragmentComponent>
) : LoginFragmentCoordinator {

    override fun exit() {
        loginFlowCoordinator.exit(componentHolder)
    }

}