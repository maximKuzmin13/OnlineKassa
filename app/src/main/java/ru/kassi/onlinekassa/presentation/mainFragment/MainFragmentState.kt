package ru.kassi.onlinekassa.presentation.mainFragment

import ru.kassi.onlinekassa.domain.api.points.Point
import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class MainFragmentState(
    val isLoading: Boolean = true,
    val link: String? = null,
    val userName: String? = null,
    val pointList: List<Point> = emptyList()
) : MviState()