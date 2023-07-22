package ru.kassi.onlinekassa

import android.app.Application
import me.vponomarenko.injectionmanager.IHasComponent
import me.vponomarenko.injectionmanager.x.XInjectionManager
import ru.kassi.onlinekassa.di.ApplicationComponent
import ru.kassi.onlinekassa.di.DaggerApplicationComponent

class App: Application(), IHasComponent<ApplicationComponent> {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    override fun getComponent(): ApplicationComponent = DaggerApplicationComponent
        .factory()
        .create(this)

    private fun initDagger() {
        XInjectionManager.init(this)
        XInjectionManager.bindComponent(this)
    }
}