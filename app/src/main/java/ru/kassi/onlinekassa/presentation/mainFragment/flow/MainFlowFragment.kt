package ru.kassi.onlinekassa.presentation.mainFragment.flow

import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.kassi.onlinekassa.flow.FlowFragment
import ru.kassi.onlinekassa.presentation.mainFragment.Screens
import ru.kassi.onlinekassa.presentation.mainFragment.di.MainFragmentComponent
import ru.kassi.onlinekassa.presentation.mainFragment.di.DaggerMainFragmentComponent

class MainFlowFragment : FlowFragment<MainFragmentComponent>(),
    IHasComponent<MainFragmentComponent> {

    override fun getComponent(): MainFragmentComponent {
        return DaggerMainFragmentComponent
            .factory()
            .create(requireContext(), XInjectionManager.findComponent())
    }

    override fun injectComponent() = XInjectionManager
        .bindComponentToCustomLifecycle(this)
        .also { it.component.inject(this) }

    override fun injectExistedComponent() = XInjectionManager
        .findComponentOrNull<MainFragmentComponent>()
        ?.inject(this) != null

    override fun getLaunchScreen(): FragmentScreen = Screens.MainScreen()
}