package ru.kassi.onlinekassa.extentions

import android.os.Parcelable
import okhttp3.ResponseBody
import ru.kassi.onlinekassa.presentation.base.BaseFragment
import ru.kassi.onlinekassa.presentation.base.mvi.MviNavArgs
import java.nio.charset.Charset

fun <NavArgs : Parcelable, State, Action, VM, TFragment : BaseFragment<NavArgs, State, Action, VM>>
        TFragment.withArgs(navArgs: MviNavArgs) = apply { initArgs(navArgs) }

fun ResponseBody.cloneBody(): String? = source()
    .also { it.request(Long.MAX_VALUE) }
    .buffer()
    ?.clone()
    ?.readString(Charset.forName("UTF-8"))