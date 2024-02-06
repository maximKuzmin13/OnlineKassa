package ru.kassi.onlinekassa.domain

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject
import kotlin.coroutines.suspendCoroutine

class RemoteConfigRepository @Inject constructor() {

    private val remoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

    fun getInstance() = remoteConfig

    suspend fun reload() = suspendCoroutine { cont ->
        remoteConfig
            .fetch(5L)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    remoteConfig.activate().addOnCompleteListener {
                        cont.resumeWith(Result.success(Unit))
                    }
                } else {
                    cont.resumeWith(Result.success(Unit))
                }
            }
    }
}