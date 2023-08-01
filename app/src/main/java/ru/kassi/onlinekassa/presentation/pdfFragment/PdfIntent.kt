package ru.kassi.onlinekassa.presentation.pdfFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class PdfIntent : MviIntent {

    object Start: PdfIntent()

    object Loading: PdfIntent()

    object Back: PdfIntent()
}