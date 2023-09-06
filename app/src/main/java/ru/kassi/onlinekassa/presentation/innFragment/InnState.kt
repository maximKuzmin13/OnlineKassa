package ru.kassi.onlinekassa.presentation.innFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class InnState(
    val isLoading: Boolean = true,
    val inn: String? = null
) : MviState()