package ru.kassi.onlinekassa.extentions

import android.app.Activity
import androidx.annotation.ColorRes
import androidx.core.view.WindowInsetsControllerCompat

fun Activity.setStatusBarColor(@ColorRes colorResId: Int) {
    val windowController = WindowInsetsControllerCompat(
        window,
        window.decorView
    )
    windowController.isAppearanceLightStatusBars = getColor(colorResId).isColorBright()
    window.statusBarColor = getColorSimple(colorResId)
}