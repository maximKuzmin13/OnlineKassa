package ru.kassi.onlinekassa.presentation.authFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class AuthState(
    val isLoading: Boolean = true,
    val loginS: String? = null,
    val innS: String? = null,
    val passS: String? = null
) : MviState()
