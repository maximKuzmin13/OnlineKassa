package ru.kassi.onlinekassa.data

import android.content.Context
import javax.inject.Inject

interface ResourceManager {
    fun getString(resId: Int): String
}

class ResourceManagerImpl @Inject constructor(
    private val context: Context
) : ResourceManager {

    override fun getString(resId: Int): String {
        return context.getString(resId)
    }
}