package ru.kassi.onlinekassa.presentation.webviewFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class WebviewIntent: MviIntent {

    object Back: WebviewIntent()
}