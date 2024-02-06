package ru.kassi.onlinekassa.presentation.launchActivity

import ru.kassi.onlinekassa.presentation.base.BaseActivity
import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class LaunchState(
    val isLoading: Boolean = true,
    val isInterrupted: Boolean = false,
    val nextDestination: Class<out BaseActivity>? = null,
) : MviState()