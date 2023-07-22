package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.presentation.loginFragment.LoginFragment
import ru.kassi.onlinekassa.presentation.mainFragment.MainFlowFragment
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragment

object Screens {

    fun Main() = FragmentScreen { MainFlowFragment.newInstance() }

    fun Login() = FragmentScreen { LoginFragment() }
}