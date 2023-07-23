package ru.kassi.onlinekassa.presentation.loginFragment.flow

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.kassi.onlinekassa.flow.FlowFragment
import ru.kassi.onlinekassa.presentation.loginFragment.di.LoginFragmentComponent
import ru.kassi.onlinekassa.presentation.loginFragment.di.DaggerLoginFragmentComponent
import ru.kassi.onlinekassa.navigation.Screens

class LoginFlowFragment : FlowFragment<LoginFragmentComponent>(),
    IHasComponent<LoginFragmentComponent> {

    override fun getComponent(): LoginFragmentComponent {
        return DaggerLoginFragmentComponent
            .factory()
            .create(requireContext(), XInjectionManager.findComponent())
    }

    override fun injectComponent() = XInjectionManager
        .bindComponentToCustomLifecycle(this)
        .also { it.component.inject(this) }

    override fun injectExistedComponent() = XInjectionManager
        .findComponentOrNull<LoginFragmentComponent>()
        ?.inject(this) != null

    override fun getLaunchScreen(): FragmentScreen = Screens.LoginScreen()
}