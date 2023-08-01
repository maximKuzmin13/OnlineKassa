package ru.kassi.onlinekassa.extentions

import androidx.core.graphics.ColorUtils
private const val IMAGE_LUMINANCE_THRESHOLD = 0.5
fun Int.isColorBright() = ColorUtils.calculateLuminance(this) > IMAGE_LUMINANCE_THRESHOLD