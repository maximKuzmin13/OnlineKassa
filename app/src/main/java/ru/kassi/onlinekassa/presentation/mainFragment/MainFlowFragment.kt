package ru.kassi.onlinekassa.presentation.mainFragment

import androidx.core.os.bundleOf
import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.kassi.onlinekassa.flow.FlowFragment
import ru.kassi.onlinekassa.navigation.Screens
import ru.kassi.onlinekassa.presentation.mainFragment.di.MainFragmentComponent
import ru.kassi.onlinekassa.presentation.mainFragment.di.DaggerMainFragmentComponent

class MainFlowFragment : FlowFragment<MainFragmentComponent>(),
    IHasComponent<MainFragmentComponent> {

    companion object {

        private const val MAIN_ARGS = "MAIN_ARGS"

        fun newInstance() =
            MainFlowFragment().apply {
                arguments = bundleOf()
            }
    }

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

    override fun getLaunchScreen(): FragmentScreen = Screens.Main()
}