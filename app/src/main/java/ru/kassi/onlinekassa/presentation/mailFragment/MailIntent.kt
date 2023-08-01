package ru.kassi.onlinekassa.presentation.mailFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class MailIntent: MviIntent {

    object Start: MailIntent()
    object Loading: MailIntent()
    object Positive: MailIntent()
    object Negative: MailIntent()
}