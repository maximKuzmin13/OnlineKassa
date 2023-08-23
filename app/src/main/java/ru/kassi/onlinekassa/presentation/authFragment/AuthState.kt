package ru.kassi.onlinekassa.presentation.authFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class AuthState(
    val isLoading: Boolean = true,
) : MviState()