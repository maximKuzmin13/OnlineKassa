package ru.kassi.onlinekassa.presentation.singleActivity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.kassi.onlinekassa.di.ApplicationComponent
import ru.kassi.onlinekassa.navigation.Screens
import ru.kassi.onlinekassa.navigation.StartUpCoordinatorImpl
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var coordinator: StartUpCoordinatorImpl


    val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)

        viewModel.goToScreen(Screens.Main())
    }



    private fun injectDependencies() {
        XInjectionManager
            .findComponent<ApplicationComponent>()
            .inject(this)
    }

}
