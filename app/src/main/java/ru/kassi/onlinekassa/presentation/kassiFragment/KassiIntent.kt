package ru.kassi.onlinekassa.presentation.kassiFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class KassiIntent: MviIntent {
    object Start: KassiIntent()
    object Loading: KassiIntent()
}