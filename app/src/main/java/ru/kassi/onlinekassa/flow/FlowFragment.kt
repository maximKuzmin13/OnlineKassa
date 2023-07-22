package ru.kassi.onlinekassa.flow

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import me.vponomarenko.injectionmanager.customlifecycle.StoredComponent
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.di.ComponentHolder
import ru.kassi.onlinekassa.loader.LoaderControllerDelegate
import ru.kassi.onlinekassa.loader.LoaderControllerViewData
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.DIMMER_DEFAULT_ALPHA
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.DIMMER_DEFAULT_COLOR
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.TOOLBAR_DEFAULT_HEIGHT
import ru.kassi.onlinekassa.navigation.CiceroneTuner
import ru.kassi.onlinekassa.navigation.FeatureScope
import ru.kassi.onlinekassa.navigation.FlowNavigation
import javax.inject.Inject

abstract class FlowFragment<TComponent>(
    @LayoutRes layoutId: Int = R.layout.fragment_flow
) : Fragment(layoutId) {

    @Inject
    @FlowNavigation
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    @FlowNavigation
    lateinit var router: Router

    @Inject
    @FeatureScope
    lateinit var componentHolder: ComponentHolder<TComponent>

    private var loader = LoaderControllerViewData.empty()

    override fun onAttach(context: Context) {
        if (!injectExistedComponent()) {
            val storedComponent = injectComponent()
            componentHolder.setStoredComponent(storedComponent)
        }
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (childFragmentManager.fragments.isEmpty()) {
            router.newRootScreen(getLaunchScreen())
        }
    }

    abstract fun injectComponent(): StoredComponent<TComponent>

    abstract fun injectExistedComponent(): Boolean

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycle.addObserver(
            CiceroneTuner(navigatorHolder = navigatorHolder, navigator = createNavigator())
        )

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, exitRouterOnBackPressed)
    }

    fun setLoader(
        isVisible: Boolean = false,
        isClickable: Boolean = false,
        isDimmed: Boolean = false,
        @ColorRes dimmerColor: Int = DIMMER_DEFAULT_COLOR,
        dimmerAlpha: Float = DIMMER_DEFAULT_ALPHA,
        isToolbarVisible: Boolean = false,
        toolbarHeight: Int = TOOLBAR_DEFAULT_HEIGHT
    ) {
        loader = loader.copy(
            isVisible = isVisible,
            isClickable = isClickable,
            isDimmed = isDimmed,
            dimmerColor = dimmerColor,
            dimmerAlpha = dimmerAlpha,
            isToolbarVisible = isToolbarVisible,
            toolbarHeight = toolbarHeight
        )
        updateLoader()
    }

    private fun updateLoader() {
        kotlin.runCatching {
            with(activity as LoaderControllerDelegate) {
                loader.isVisible?.let { setLoaderVisible(it) }
                setLoaderClickable(loader.isClickable)
                setLoaderDimmerVisible(loader.isDimmed)
                setLoaderDimmerColor(loader.dimmerColor)
                setLoaderDimmerAlpha(loader.dimmerAlpha)
                setLoaderToolbarVisible(loader.isToolbarVisible, loader.toolbarHeight)
            }
        }
    }

    open fun createNavigator(): Navigator = AppNavigator(
        requireActivity(),
        0,
        childFragmentManager
    )

    private val exitRouterOnBackPressed = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            componentHolder.destroy()
            router.exit()
        }
    }

    abstract fun getLaunchScreen(): FragmentScreen

    override fun onDestroyView() {
        super.onDestroyView()
        setLoader(isVisible = false)
    }
}