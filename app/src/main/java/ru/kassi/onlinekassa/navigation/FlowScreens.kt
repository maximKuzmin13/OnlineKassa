package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.presentation.loginFragment.flow.LoginFlowFragment
import ru.kassi.onlinekassa.presentation.mainFragment.flow.MainFlowFragment

object FlowScreens {

    fun Main() = FragmentScreen { MainFlowFragment() }

    fun Login() = FragmentScreen { LoginFlowFragment() }
}