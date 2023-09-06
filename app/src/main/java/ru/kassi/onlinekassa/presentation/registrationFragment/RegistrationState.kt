package ru.kassi.onlinekassa.presentation.registrationFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class RegistrationState(
    val isLoading: Boolean = true,
    val loginS: String? = null,
    val innS: String? = null,
    val passS: String? = null
) : MviState()