package ru.kassi.onlinekassa.presentation.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.News
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.domain.LoadKassaInfoUseCase
import ru.kassi.onlinekassa.domain.api.points.Point
import ru.kassi.onlinekassa.domain.api.points.PointRepository
import ru.kassi.onlinekassa.domain.api.profile.ProfileRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.mainFragment.coordinator.MainFragmentCoordinator
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val coordinator: MainFragmentCoordinator,
    private val pointRepository: PointRepository,
    private val profileRepository: ProfileRepository,
    private val resources: ResourceManager,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
    private val kassaInfoUseCase: LoadKassaInfoUseCase,
    private val remoteConfigUseCase: FetchRemoteConfigUseCase,
) : MviViewModel<EmptyNavArgs, MainFragmentState, MainFragmentIntent>(MainFragmentState()) {

    val _errorToast: MutableLiveData<String> = MutableLiveData()
    val errorToast: LiveData<String> = _errorToast

    private val handler = CoroutineExceptionHandler { _, throwable ->
        handleError(throwable)
    }

    init {
        getProfile()
        loadPoints()
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

    private fun getProfile(){
        viewModelScope.launch(handler + dispatcher){
            delay(100)
            val userName = profileRepository.getProfile()?.response?.fio
            val temp = userName?.substringAfter(" ")
            val name = temp?.substringBefore(" ")
            _state.value = currentState.copy(
                userName = name
            )
        }
    }

    private fun loadPoints() {
        viewModelScope.launch(handler + dispatcher) {
            val pointList = pointRepository.getPointsList().response
            val format = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val currentDate = SimpleDateFormat("dd.MM.yyyy").parse(LocalDateTime.now().format(format))
           pointList?.forEach { point ->
                val list = kassaInfoUseCase.loadKassaInfo(point.num.toString())
                list.filter { it.term.isNullOrEmpty() }
                list.map {
                    val term = it.term?.substringBefore("T")
                    val dateD = SimpleDateFormat("yyyy-MM-dd").parse(term)
                    val diff: Long = currentDate.time - dateD.time
                    val seconds = diff / 1000
                    val minutes = seconds / 60
                    val hours = minutes / 60
                    val days = (hours / 24) * -1
                    if (days < 45) {
                        point.haveExpiredKassa = true
                        _state.value = currentState.copy(
                            haveExpiredKass = true
                        )
                    }
                }
            }
            _state.value = currentState.copy(
                pointList = pointList
            )
        }
    }

    private fun handleError(e: Throwable) {
        _errorToast.value = e.message
    }
}
