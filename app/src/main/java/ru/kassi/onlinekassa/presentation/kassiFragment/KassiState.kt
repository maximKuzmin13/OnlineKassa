package ru.kassi.onlinekassa.presentation.kassiFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class KassiState(
    val isLoading: Boolean = true,
) : MviState()