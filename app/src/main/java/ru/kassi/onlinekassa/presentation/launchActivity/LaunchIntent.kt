package ru.kassi.onlinekassa.presentation.launchActivity

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class LaunchIntent : MviIntent {
    object Interrupt : LaunchIntent()
    object FetchData : LaunchIntent()
}