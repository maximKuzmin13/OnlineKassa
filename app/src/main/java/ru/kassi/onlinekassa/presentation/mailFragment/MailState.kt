package ru.kassi.onlinekassa.presentation.mailFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class MailState(
    val isLoading: Boolean = true,
) : MviState()