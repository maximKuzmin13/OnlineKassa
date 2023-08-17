package ru.kassi.onlinekassa.presentation.webviewFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class WebViewState(
    val isLoading: Boolean = true,
) : MviState()