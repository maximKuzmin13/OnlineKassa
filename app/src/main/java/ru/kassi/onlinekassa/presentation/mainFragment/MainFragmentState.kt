package ru.kassi.onlinekassa.presentation.mainFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class MainFragmentState(
    val isLoading: Boolean = true,
    val onClick: Int? = null
) : MviState()