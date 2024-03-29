package ru.kassi.onlinekassa.presentation.base

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.extentions.setStatusBarColor
import ru.kassi.onlinekassa.loader.LoaderControllerDelegate
import ru.kassi.onlinekassa.loader.LoaderControllerViewData
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.DIMMER_DEFAULT_ALPHA
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.DIMMER_DEFAULT_COLOR
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.TOOLBAR_DEFAULT_HEIGHT
import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent
import ru.kassi.onlinekassa.presentation.base.mvi.MviNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviState
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel

abstract class BaseFragment<NavArgs, State, Action, VM>: Fragment
        where NavArgs : Parcelable,
              State : MviState,
              Action : MviIntent,
              VM : MviViewModel<NavArgs, State, Action> {

    constructor() : super()

    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)
    companion object {
        const val INIT_ARGS_KEY = "INIT_ARGS"
    }

    protected abstract val viewModel: VM
    protected val context: Context
        @JvmName("requireContextKtx") get() = requireContext()

    private var loader = LoaderControllerViewData.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.setStatusBarColor(R.color.white)
        setHasOptionsMenu(true)
        updateLoader()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            viewModel.state.collectLatest {
                renderState(it)
            }
        }
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

    @ColorInt
    fun getColor(@ColorRes resId: Int): Int = ContextCompat.getColor(requireContext(), resId)

    fun getColorStateList(@ColorRes resId: Int): ColorStateList? =
        ContextCompat.getColorStateList(context, resId)

    fun getDrawable(@DrawableRes resId: Int): Drawable? = ContextCompat.getDrawable(context, resId)

    fun initArgs(navArgs: MviNavArgs) {
        arguments?.putParcelable(INIT_ARGS_KEY, navArgs)
    }

    protected open fun renderState(viewState: State) {}

    override fun onDestroyView() {
        super.onDestroyView()
        setLoader(isVisible = false)
    }
}