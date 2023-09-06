package ru.kassi.onlinekassa.presentation.kassiFragment

import ru.kassi.onlinekassa.domain.api.kassa.Kassa
import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class KassiState(
    val isLoading: Boolean = true,
    val num: String? = null,
    val kassaList: List<Pair<String?, String?>> = emptyList()
) : MviState()