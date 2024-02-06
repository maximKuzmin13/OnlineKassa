package ru.kassi.onlinekassa.domain

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import ru.kassi.onlinekassa.data.News
import ru.kassi.onlinekassa.extentions.JsonValidation
import javax.inject.Inject

class FetchRemoteConfigUseCase @Inject constructor(
    private val remoteConfigRepository: RemoteConfigRepository,
    private val moshi: Moshi
) {

    fun getNews() : List<News>? {
        val newsListType = Types.newParameterizedType(MutableList::class.java, News::class.java)
        val newsAdapter = moshi.adapter<List<News>>(newsListType)
        return remoteConfigRepository.getInstance().getString("news_list").let {
            val (isValid, list) = JsonValidation.validateAndGet {
                newsAdapter.fromJson(it)
            }
            if (isValid) list else null
        }
    }

    fun getInstance() = remoteConfigRepository.getInstance()

    suspend fun reload() = remoteConfigRepository.reload()

}