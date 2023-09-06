package ru.kassi.onlinekassa.presentation.innFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent
import ru.kassi.onlinekassa.presentation.kassiFragment.KassiIntent

sealed class InnIntent : MviIntent {

    object Loading: InnIntent()
    object Next: InnIntent()
    data class Inn(val innS: String): InnIntent()
    object Back: InnIntent()
}