package ru.kassi.onlinekassa.domain

import javax.inject.Inject

class FetchRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository
) {

    fun fetch() = remoteConfigRepository.fetchConfig()

    fun getAll() = remoteConfigRepository.getFullConfig()

    fun getInstance() = remoteConfigRepository.getInstance()

    suspend fun reload() = remoteConfigRepository.reload()

}