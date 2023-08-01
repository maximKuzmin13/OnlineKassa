package ru.kassi.onlinekassa.presentation.base

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.extentions.setStatusBarColor
import ru.kassi.onlinekassa.loader.LoaderControllerDelegate
import ru.kassi.onlinekassa.loader.LoaderControllerViewData
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.DIMMER_DEFAULT_ALPHA
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.DIMMER_DEFAULT_COLOR
import ru.kassi.onlinekassa.loader.LoaderControllerViewData.Companion.TOOLBAR_DEFAULT_HEIGHT

open class BaseFragment: Fragment {

    constructor() : super()

    constructor(@LayoutRes layoutRes: Int) : super(layoutRes)

    protected val context: Context
        @JvmName("requireContextKtx") get() = requireContext()

    private var loader = LoaderControllerViewData.empty()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.setStatusBarColor(R.color.white)
        setHasOptionsMenu(true)
        updateLoader()
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

    override fun onDestroyView() {
        super.onDestroyView()
        setLoader(isVisible = false)
    }
}