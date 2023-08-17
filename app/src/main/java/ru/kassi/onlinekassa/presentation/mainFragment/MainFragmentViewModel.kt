package ru.kassi.onlinekassa.presentation.mainFragment

import android.util.Log
import com.google.firebase.remoteconfig.ktx.get
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import ru.kassi.onlinekassa.data.News
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val coordinator: MainFragmentCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val remoteConfigUseCase: FetchRemoteConfigUseCase
) : MviViewModel<MainFragmentState, MainFragmentIntent>(MainFragmentState()) {

    fun goToProfile() {
        coordinator.goToProfile()
    }

    override val onError: suspend (Throwable) -> Unit = {}

    fun showNews(): Boolean = remoteConfigUseCase.getInstance().getBoolean("showNews")

    fun getNewsList(): List<News> = remoteConfigUseCase.getNews().orEmpty()

    override suspend fun reduceState(intent: MainFragmentIntent): MainFragmentState {
        return when (intent) {
            MainFragmentIntent.Loading -> currentState
            MainFragmentIntent.Start -> currentState
            is MainFragmentIntent.PointClick -> {
                coordinator.goToKassa(intent.pointId)
                currentState
            }

            is MainFragmentIntent.NewsClick -> {
                coordinator.goToWebView(intent.link)
                currentState
            }
        }
    }

}
