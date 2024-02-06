package ru.kassi.onlinekassa.presentation.pinFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class PinState(
    val isLoading: Boolean = true,
) : MviState()