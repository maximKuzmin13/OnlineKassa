package ru.kassi.onlinekassa.presentation.mainFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.News
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.domain.AuthRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val coordinator: MainFragmentCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val remoteConfigUseCase: FetchRemoteConfigUseCase
) : MviViewModel<EmptyNavArgs, MainFragmentState, MainFragmentIntent>(MainFragmentState()) {

    init {

    }

    fun goToProfile() {
        coordinator.goToProfile()
    }

    override val onError: suspend (Throwable) -> Unit = {}

    fun showNews(): Boolean = remoteConfigUseCase.getInstance().getBoolean("showNews")

    fun getNewsList(): List<News> = remoteConfigUseCase.getNews().orEmpty()

    override suspend fun reduceState(intent: MainFragmentIntent) {
        return when (intent) {
            MainFragmentIntent.Loading -> {}
            MainFragmentIntent.Start -> {}
            is MainFragmentIntent.PointClick -> {
                coordinator.goToKassa(intent.pointId)
            }

            is MainFragmentIntent.NewsClick -> {
                coordinator.goToWebView(intent.link)
            }
        }
    }

}
