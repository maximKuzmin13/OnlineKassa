package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.presentation.kassiFragment.KassiFragment
import ru.kassi.onlinekassa.presentation.loginFragment.LoginFragment
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragment
import ru.kassi.onlinekassa.presentation.pdfFragment.PdfFragment
import ru.kassi.onlinekassa.presentation.pinFragment.PinFragment
import ru.kassi.onlinekassa.presentation.profileFragment.ProfileFragment

object Screens {

    fun MainScreen() = FragmentScreen { MainFragment() }

    fun LoginScreen() = FragmentScreen { LoginFragment() }

    fun KassaScreen(id: Int) = FragmentScreen { KassiFragment() }

    fun ProfileScreen() = FragmentScreen { ProfileFragment() }

    fun PinScreen() = FragmentScreen { PinFragment() }

    fun PdfScreen() = FragmentScreen { PdfFragment() }
}