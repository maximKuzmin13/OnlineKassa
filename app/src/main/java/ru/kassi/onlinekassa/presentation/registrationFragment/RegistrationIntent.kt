package ru.kassi.onlinekassa.presentation.registrationFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class RegistrationIntent: MviIntent {
    object Start: RegistrationIntent()
    object Loading: RegistrationIntent()
    object Next: RegistrationIntent()
    data class Login(val login: String): RegistrationIntent()
    data class Inn(val inn: String): RegistrationIntent()
    data class Pass(val pass: String): RegistrationIntent()
}