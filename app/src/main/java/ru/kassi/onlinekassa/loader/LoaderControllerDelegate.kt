package ru.kassi.onlinekassa.loader

import android.widget.FrameLayout
import androidx.annotation.ColorRes

interface LoaderControllerDelegate {

    fun initLoader(loader: FrameLayout)

    fun setLoaderVisible(isVisible: Boolean)

    fun setLoaderClickable(isClickable: Boolean)

    fun setLoaderDimmerVisible(isVisible: Boolean)

    fun setLoaderDimmerColor(@ColorRes color: Int)

    fun setLoaderDimmerAlpha(alpha: Float)

    fun setLoaderToolbarVisible(isVisible: Boolean, toolbarHeight: Int)
}
