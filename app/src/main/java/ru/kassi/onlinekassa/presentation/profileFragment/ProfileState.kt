package ru.kassi.onlinekassa.presentation.profileFragment

import ru.kassi.onlinekassa.data.Profile
import ru.kassi.onlinekassa.presentation.base.mvi.MviState

data class ProfileState(
    val isLoading: Boolean = true,
    val profile: List<Profile> = emptyList()
) : MviState()