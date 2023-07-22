package ru.kassi.onlinekassa.presentation.mainFragment

import ru.kassi.onlinekassa.di.ComponentHolder
import ru.kassi.onlinekassa.presentation.mainFragment.di.MainFragmentComponent
import javax.inject.Inject

class MainFragmentCoordinatorImpl @Inject constructor(
    private val mainBottomFlowCoordinator: MainFlowCoordinator,
    private val componentHolder: ComponentHolder<MainFragmentComponent>
) : MainFragmentCoordinator {

    override fun exit() {
        mainBottomFlowCoordinator.exit(componentHolder)
    }

}