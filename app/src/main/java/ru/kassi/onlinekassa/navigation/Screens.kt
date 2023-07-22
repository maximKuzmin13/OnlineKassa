package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.presentation.loginFragment.LoginFragment
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowFragment

object Screens {

    fun Main() = FragmentScreen { MainFlowFragment() }

    fun Login() = FragmentScreen { LoginFragment() }
}