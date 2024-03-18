package ru.kassi.onlinekassa.presentation.profileFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent
import ru.kassi.onlinekassa.presentation.kassiFragment.KassiIntent

sealed class ProfileIntent: MviIntent {
    object Start: ProfileIntent()
    object Loading: ProfileIntent()
    object Back: ProfileIntent()

    object Logout: ProfileIntent()
}