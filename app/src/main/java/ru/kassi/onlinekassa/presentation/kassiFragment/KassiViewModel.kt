package ru.kassi.onlinekassa.presentation.kassiFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.ModelData
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.api.kassa.KassaData
import ru.kassi.onlinekassa.domain.api.kassa.KassaRepository
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.kassiFragment.coordinator.KassiCoordinator
import javax.inject.Inject

@HiltViewModel
class KassiViewModel @Inject constructor(
    private val kassiCoordinator: KassiCoordinator,
    private val kassaRepository: KassaRepository,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : MviViewModel<KassaNavArgs, KassiState, KassiIntent>(KassiState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    override suspend fun reduceState(intent: KassiIntent) {
        return when (intent) {
            KassiIntent.Loading -> {}
            KassiIntent.Start -> {}
            KassiIntent.Back -> {
                kassiCoordinator.goToMain()
            }
            is KassiIntent.LoadPdf -> {
                loadPdf(intent.modelData)
            }
            is KassiIntent.Num -> {
                _state.value = currentState.copy(num = intent.num)
                loadKassaInfo()
            }
        }
    }

    fun loadKassaInfo() {
        viewModelScope.launch {
            val kassa = kassaRepository.getKassaInfo(currentState.num.orEmpty()).response
            val list = mutableListOf<KassaData>()
            list.add(
                KassaData(
                    name = kassa.name,
                    address = kassa.address,
                    service = kassa.FNmodel,
                    term = kassa.FNdate,
                    kind = kassa.FNtype != "Нет"
                ),
            )
            list.add(
                KassaData(
                    name = kassa.name,
                    address = kassa.address,
                    service = kassa.OFDname,
                    term = kassa.OFDdate,
                    kind = when (kassa.OFDpin) {
                        "15 мес" -> false
                        "36 мес" -> true
                        else -> null
                    }
                ),
            )
            list.add(
                KassaData(
                    name = kassa.name,
                    address = kassa.address,
                    service = kassa.CassPO,
                    term = kassa.CassPOdate,
                    kind = null
                ),
            )
            list.add(
                KassaData(
                    name = kassa.name,
                    address = kassa.address,
                    service = kassa.CassPOobnovl,
                    term = kassa.CassPOobnovlDate,
                    kind = null
                ),
            )
            list.add(
                KassaData(
                    name = kassa.name,
                    address = kassa.address,
                    service = kassa.tovaroUch,
                    term = kassa.tovaroUchDate,
                    kind = null
                ),
            )
            list.removeIf { it.term.isNullOrEmpty() }
            _state.value = currentState.copy(
                kassaList = list.toList()
            )
        }
    }

    fun loadPdf(modelData: ModelData?) {
        viewModelScope.launch {
            kassiCoordinator.goToPdf(modelData?.model.orEmpty(), modelData?.type, currentState.num ?: "0")
        }
    }
}