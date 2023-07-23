package ru.kassi.onlinekassa.presentation.singleActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.di.ApplicationComponent
import ru.kassi.onlinekassa.di.ViewModelAssistedFactory
import ru.kassi.onlinekassa.di.ViewModelFactory
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.Screens
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    @MainNavigation
    lateinit var router: Router

    @Inject
    @MainNavigation
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = AppNavigator(this, R.id.fragment_container)


    private val viewModel: MainViewModel by viewModel()

    @Inject
    lateinit var viewModelMap: MutableMap<Class<out ViewModel>, ViewModelAssistedFactory<out ViewModel>>

    @MainThread
    private fun viewModel(): Lazy<MainViewModel> =
        lazy {
            ViewModelProvider(
                viewModelStore,
                ViewModelFactory(viewModelMap, this, bundleOf())
            ).get(MainViewModel::class.java)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.goToScreen(Screens.MainScreen())
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
    private fun injectDependencies() {
        XInjectionManager
            .findComponent<ApplicationComponent>()
            .inject(this)
    }

}
