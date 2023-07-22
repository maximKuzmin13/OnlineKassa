package ru.kassi.onlinekassa.di

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val viewModelMap: MutableMap<Class<out ViewModel>, ViewModelAssistedFactory<out ViewModel>>,
    owner: SavedStateRegistryOwner,
    arguments: Bundle
) : AbstractSavedStateViewModelFactory(owner, arguments) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return viewModelMap[modelClass]?.create(handle) as? T ?: throw IllegalStateException("Unknown ViewModel class")
    }

}