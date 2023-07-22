package ru.kassi.onlinekassa.di

import me.vponomarenko.injectionmanager.customlifecycle.StoredComponent
import ru.kassi.onlinekassa.navigation.FeatureScope
import javax.inject.Inject

@FeatureScope
class ComponentHolder<TComponent> @Inject constructor(){

    private var storedComponent: StoredComponent<TComponent>? = null

    fun setStoredComponent(storedComponent: StoredComponent<TComponent>) {
        this.storedComponent = storedComponent
    }

    fun destroy() {
        storedComponent?.lifecycle?.destroy()
    }

}