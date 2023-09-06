package ru.kassi.onlinekassa.presentation.pdfFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class PdfState(
    val isLoading: Boolean = true,
    val num: String? = null
) : MviState()