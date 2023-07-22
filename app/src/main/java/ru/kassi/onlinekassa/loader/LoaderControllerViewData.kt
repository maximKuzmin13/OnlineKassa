package ru.kassi.onlinekassa.loader

import androidx.annotation.ColorRes

/**
 * *Loader Controller*
 *
 * @param isVisible для включения и выключения лоадера
 * @param isClickable позволяет включить или выключить нажатие на элементы под лоадером,
 * *значение по умолчанию: false*
 * @param isDimmed управления затемнением за лоадером, *значение по умолчанию: false*
 * @param dimmerColor цвет затемнения лоадера, *цвет по умолчанию: темно сервый*
 * @param dimmerAlpha прозрачность затемнения за лоадером, *прозрачность по умолчанию: 0.3*
 * @param isToolbarVisible чтобы лоадер не перекрывал тулбар, *значение по умолчанию: false*
 * @param toolbarHeight задать в ручную размер тулбара, для того чтобы лоадер его не перекрывал.
 * Работает совместно с isToolbarVisible, *значени по умолчанию: 56dp*
 *
 */
data class LoaderControllerViewData(
    val isVisible: Boolean?,
    val isClickable: Boolean,
    val isDimmed: Boolean,
    @ColorRes val dimmerColor: Int,
    val dimmerAlpha: Float,
    val isToolbarVisible: Boolean,
    val toolbarHeight: Int
) {
    companion object {
        const val DIMMER_DEFAULT_ALPHA = .3F;
        const val DIMMER_DEFAULT_COLOR = android.R.color.darker_gray
        const val DIMMER_WHITE_ALPHA = .7F
        const val DIMMER_WHITE_COLOR = android.R.color.white
        const val TOOLBAR_DEFAULT_HEIGHT = 56
        const val FULL_COLOR_ALPHA = 1F

        fun empty() = LoaderControllerViewData(
            isVisible = null,
            isClickable = false,
            isDimmed = false,
            dimmerColor = DIMMER_DEFAULT_COLOR,
            dimmerAlpha = DIMMER_DEFAULT_ALPHA,
            isToolbarVisible = false,
            toolbarHeight = TOOLBAR_DEFAULT_HEIGHT
        )
    }
}
