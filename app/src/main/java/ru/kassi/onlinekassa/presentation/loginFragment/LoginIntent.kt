package ru.kassi.onlinekassa.presentation.loginFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class LoginIntent: MviIntent {
    object Start: LoginIntent()
    object Loading: LoginIntent()
}