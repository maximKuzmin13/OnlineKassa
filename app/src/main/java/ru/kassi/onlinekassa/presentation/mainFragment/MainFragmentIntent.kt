package ru.kassi.onlinekassa.presentation.mainFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class MainFragmentIntent: MviIntent {
    object Start: MainFragmentIntent()
    object Loading: MainFragmentIntent()
}