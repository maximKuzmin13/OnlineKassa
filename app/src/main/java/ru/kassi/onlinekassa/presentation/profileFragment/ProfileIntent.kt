package ru.kassi.onlinekassa.presentation.profileFragment

import ru.kassi.onlinekassa.presentation.base.mvi.MviIntent

sealed class ProfileIntent: MviIntent {
    object Start: ProfileIntent()
    object Loading: ProfileIntent()
}