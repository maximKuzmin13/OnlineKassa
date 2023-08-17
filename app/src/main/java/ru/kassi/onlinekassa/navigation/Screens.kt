package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.presentation.kassiFragment.KassiFragment
import ru.kassi.onlinekassa.presentation.loginFragment.LoginFragment
import ru.kassi.onlinekassa.presentation.mailFragment.MailFragment
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragment
import ru.kassi.onlinekassa.presentation.pdfFragment.PdfFragment
import ru.kassi.onlinekassa.presentation.pinFragment.PinFragment
import ru.kassi.onlinekassa.presentation.profileFragment.ProfileFragment
import ru.kassi.onlinekassa.presentation.webviewFragment.WebViewFragment
import ru.kassi.onlinekassa.presentation.webviewFragment.WebviewNavArgs

object Screens {

    fun MainScreen() = FragmentScreen { MainFragment() }

    fun LoginScreen() = FragmentScreen { LoginFragment() }

    fun MailScreen() = FragmentScreen { MailFragment() }

    fun KassaScreen(id: Int) = FragmentScreen { KassiFragment() }

    fun ProfileScreen() = FragmentScreen { ProfileFragment() }

    fun PinScreen() = FragmentScreen { PinFragment() }

    fun PdfScreen() = FragmentScreen { PdfFragment() }

    fun WebviewScreen(link: String) = FragmentScreen { WebViewFragment.newInstance(WebviewNavArgs(link))}
}