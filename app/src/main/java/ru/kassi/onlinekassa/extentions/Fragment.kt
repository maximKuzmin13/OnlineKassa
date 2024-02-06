package ru.kassi.onlinekassa.extentions

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.MviNavArgs
import kotlin.properties.ReadWriteProperty

inline fun <reified T> Fragment.args(
    key: String? = null,
    defaultValue: T? = null
): ReadWriteProperty<Fragment, T> {
    return BundleExtractorDelegate { thisRef, property ->
        val bundleKey = key ?: property.name
        extractFromBundle(thisRef.arguments, bundleKey, defaultValue)
    }
}

fun <T : Fragment> T.withArgs(receiver: Bundle.() -> Unit): T {
    arguments = Bundle().apply(receiver)
    return this
}

inline fun <reified T> extractFromBundle(
    bundle: Bundle?,
    key: String? = null,
    defaultValue: T? = null
): T {
    val result = bundle?.get(key) ?: defaultValue
    if (result != null && result !is T) {
        throw ClassCastException("Property for $key has different class type")
    }
    return result as T
}