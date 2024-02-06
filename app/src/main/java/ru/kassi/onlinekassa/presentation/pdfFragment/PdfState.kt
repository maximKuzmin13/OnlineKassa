package ru.kassi.onlinekassa.presentation.pdfFragment

import ru.kassi.onlinekassa.data.ModelType
import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class PdfState(
    val isLoading: Boolean = true,
    val mail: String? = null,
    val num: String? = null,
    val bankName: String? = null,
    val bic: String? = null,
    val corrAcc: String? = null,
    val name: String? = null,
    val inn: String? = null,
    val personalAcc: String? = null,
    val model: ModelType? = null,
    val typeSum: String? = null
) : MviState()