package ru.kassi.onlinekassa.extentions

import android.content.Context
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

fun Context.getColorSimple(@ColorRes id: Int): Int = ContextCompat.getColor(this, id)