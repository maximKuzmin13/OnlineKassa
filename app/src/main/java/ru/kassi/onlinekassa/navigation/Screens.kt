package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.presentation.loginFragment.LoginFragment
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragment

object Screens {

    fun MainScreen() = FragmentScreen { MainFragment() }

    fun LoginScreen() = FragmentScreen { LoginFragment() }
}