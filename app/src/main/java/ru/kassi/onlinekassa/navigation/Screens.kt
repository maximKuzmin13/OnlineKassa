package ru.kassi.onlinekassa.navigation

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.kassi.onlinekassa.presentation.authFragment.AuthFragment
import ru.kassi.onlinekassa.presentation.authFragment.AuthNavArgs
import ru.kassi.onlinekassa.presentation.innFragment.InnFragment
import ru.kassi.onlinekassa.presentation.kassiFragment.KassaNavArgs
import ru.kassi.onlinekassa.presentation.kassiFragment.KassiFragment
import ru.kassi.onlinekassa.presentation.loginFragment.LoginFragment
import ru.kassi.onlinekassa.presentation.mailFragment.MailFragment
import ru.kassi.onlinekassa.presentation.mainFragment.MainFragment
import ru.kassi.onlinekassa.presentation.pdfFragment.PdfFragment
import ru.kassi.onlinekassa.presentation.pinFragment.PinFragment
import ru.kassi.onlinekassa.presentation.profileFragment.ProfileFragment
import ru.kassi.onlinekassa.presentation.registrationFragment.RegistrationFragment
import ru.kassi.onlinekassa.presentation.webviewFragment.WebViewFragment
import ru.kassi.onlinekassa.presentation.webviewFragment.WebviewNavArgs

object Screens {

    fun MainScreen() = FragmentScreen { MainFragment() }

    fun LoginScreen() = FragmentScreen { LoginFragment() }
    fun AuthScreen(inn: String) = FragmentScreen { AuthFragment.newInstance(AuthNavArgs(inn)) }
    fun RegisterScreen() = FragmentScreen { RegistrationFragment() }
    fun InnScreen() = FragmentScreen { InnFragment() }

    fun MailScreen() = FragmentScreen { MailFragment() }

    fun KassaScreen(id: String) = FragmentScreen { KassiFragment.newInstance(KassaNavArgs(num = id)) }

    fun ProfileScreen() = FragmentScreen { ProfileFragment() }

    fun PinScreen() = FragmentScreen { PinFragment() }

    fun PdfScreen() = FragmentScreen { PdfFragment() }

    fun WebviewScreen(link: String) = FragmentScreen { WebViewFragment.newInstance(WebviewNavArgs(link))}
}