package ru.kassi.onlinekassa.presentation.mainFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class MainFragmentIntent: MviIntent {
    object Start: MainFragmentIntent()
    object Loading: MainFragmentIntent()
    class PointClick(val pointId: String): MainFragmentIntent()
    class NewsClick(val link: String): MainFragmentIntent()
}