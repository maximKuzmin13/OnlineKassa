package ru.kassi.onlinekassa.presentation.authFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent
import ru.kassi.onlinekassa.presentation.pinFragment.PinIntent

sealed class AuthIntent: MviIntent {
    object Start: AuthIntent()
    object Loading: AuthIntent()
    object Next: AuthIntent()
}