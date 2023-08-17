package ru.kassi.onlinekassa.presentation.singleActivity

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.github.terrakok.cicerone.Navigator
import com.github.terrakok.cicerone.NavigatorHolder
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.hilt.android.AndroidEntryPoint
import ru.kassi.onlinekassa.R
import ru.kassi.onlinekassa.di.ViewModelFactory
import ru.kassi.onlinekassa.navigation.MainActivityNavigator
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.navigation.StartUpCoordinator
import ru.kassi.onlinekassa.presentation.base.BaseActivity
import javax.inject.Inject

@AndroidEntryPoint
class SingleActivity : BaseActivity() {

    @Inject
    lateinit var startUpCoordinator: StartUpCoordinator

    @Inject
    @MainNavigation
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = MainActivityNavigator(this)

    @Inject
    lateinit var factory: ViewModelFactory

    override val view: Int = R.layout.activity_main

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRemoteConfig()
        startUpCoordinator.goToMainOrAuth()
    }

    private fun initRemoteConfig() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val configSettings = FirebaseRemoteConfigSettings.Builder()
            .setMinimumFetchIntervalInSeconds(2)
            .build()
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        fetch()
    }

    private fun fetch() {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated. Fetch and activate succeeded") // updated = false
                } else {
                    Log.d(TAG, "Fetch failed")
                }
            }
    }
}
