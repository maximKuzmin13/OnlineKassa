package ru.kassi.onlinekassa.di

import android.content.Context
import com.github.terrakok.cicerone.Router
import dagger.BindsInstance
import dagger.Component
import ru.kassi.onlinekassa.App
import ru.kassi.onlinekassa.navigation.MainNavigation
import ru.kassi.onlinekassa.presentation.singleActivity.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        MainNavigationModule::class,
        ViewModelModule::class,
    ]
)
interface ApplicationComponent {

    @MainNavigation
    fun router(): Router

    fun inject(application: App)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): ApplicationComponent
    }
}