package ru.kassi.onlinekassa.presentation.loginFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class LoginState(
    val isLoading: Boolean = true,
) : MviState()