package ru.kassi.onlinekassa.presentation.pinFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class PinIntent: MviIntent {
    object Start: PinIntent()
    object Loading: PinIntent()
}