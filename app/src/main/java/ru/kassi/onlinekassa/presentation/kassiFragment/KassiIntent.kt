package ru.kassi.onlinekassa.presentation.kassiFragment

import ru.kassi.onlinekassa.data.ModelData
import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class KassiIntent: MviIntent {
    object Start: KassiIntent()
    object Loading: KassiIntent()
    object Back: KassiIntent()
    data class LoadPdf (val modelData: ModelData?): KassiIntent()

    data class Num(val num: String): KassiIntent()
}