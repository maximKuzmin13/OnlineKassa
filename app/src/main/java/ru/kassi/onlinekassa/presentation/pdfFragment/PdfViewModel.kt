package ru.kassi.onlinekassa.presentation.pdfFragment

import androidx.lifecycle.viewModelScope
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import ru.kassi.onlinekassa.data.ModelType
import ru.kassi.onlinekassa.data.ResourceManager
import ru.kassi.onlinekassa.di.IoDispatcher
import ru.kassi.onlinekassa.domain.FetchRemoteConfigUseCase
import ru.kassi.onlinekassa.presentation.base.mvi.EmptyNavArgs
import ru.kassi.onlinekassa.presentation.base.mvi.MviViewModel
import ru.kassi.onlinekassa.presentation.pdfFragment.coordinator.PdfCoordinator
import javax.inject.Inject

@HiltViewModel
class PdfViewModel @Inject constructor(
    private val coordinator: PdfCoordinator,
    private val resources: ResourceManager,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val remoteConfigUseCase: FetchRemoteConfigUseCase
) : MviViewModel<EmptyNavArgs, PdfState, PdfIntent>(PdfState()) {
    override val onError: suspend (Throwable) -> Unit = {}

    private var navArgs: PdfNavArgs? = null

    override suspend fun reduceState(intent: PdfIntent) {
        return when (intent) {
            PdfIntent.Loading -> {}
            PdfIntent.Start -> {}
            PdfIntent.Back -> {
                coordinator.backToKassi(currentState.num ?: "0")
            }

            is PdfIntent.Num -> _state.value = currentState.copy(num = intent.num)
        }
    }

    fun loadIpData(remoteConfig: FirebaseRemoteConfig) {
        viewModelScope.launch {
            _state.value = currentState.copy(isLoading = true)
            _state.value = currentState.copy(
                name = remoteConfig.getString("name"),
                inn = remoteConfig.getString("payeelNN"),
                bankName = remoteConfig.getString("bank_name"),
                bic = remoteConfig.getString("bic"),
                corrAcc = remoteConfig.getString("corresp_acc"),
                personalAcc = remoteConfig.getString("personal_acc")
            )
            _state.value = currentState.copy(
                isLoading = false
            )
        }
    }

    fun loadScomData(remoteConfig: FirebaseRemoteConfig) {
        viewModelScope.launch {
            _state.value = currentState.copy(isLoading = true)
            _state.value = currentState.copy(
                name = remoteConfig.getString("scom_name"),
                inn = remoteConfig.getString("payeelNN"),
                bankName = remoteConfig.getString("scom_bank_name"),
                bic = remoteConfig.getString("bic"),
                corrAcc = remoteConfig.getString("scom_corr_acc"),
                personalAcc = remoteConfig.getString("scom_personal_acc")
            )
            _state.value = currentState.copy(
                isLoading = false
            )
        }
    }

    fun loadServicecomData(remoteConfig: FirebaseRemoteConfig) {
        viewModelScope.launch {
            _state.value = currentState.copy(isLoading = true)
            _state.value = currentState.copy(
                name = remoteConfig.getString("servicecom_name"),
                inn = remoteConfig.getString("payeelNN"),
                bankName = remoteConfig.getString("servicecom_bank_name"),
                bic = remoteConfig.getString("bic"),
                corrAcc = remoteConfig.getString("servicecom_corr_acc"),
                personalAcc = remoteConfig.getString("servicecom_personal_acc")
            )
            _state.value = currentState.copy(
                isLoading = false
            )
        }
    }

    fun loadSidorovData(config: FirebaseRemoteConfig) {
        viewModelScope.launch {
            _state.value = currentState.copy(isLoading = true)
            _state.value = currentState.copy(
                name = config.getString("sidorov_name"),
                inn = config.getString("payeelNN"),
                bankName = config.getString("sidorov_bank_name"),
                bic = config.getString("bic"),
                corrAcc = config.getString("sidorov_corr_acc"),
                personalAcc = config.getString("sidorov_personal_acc")
            )
            _state.value = currentState.copy(
                isLoading = false
            )
        }
    }

    fun chooseModel(pdfNavArgs: PdfNavArgs) {
        val model = pdfNavArgs.model
        this.navArgs = pdfNavArgs
        val remoteConfig = remoteConfigUseCase.getInstance()
        _state.value = currentState.copy(
            mail = remoteConfig.getString("mail")
        )
        when {
            model.contains("ФН") -> {
                loadScomData(remoteConfig)
                _state.value = currentState.copy(
                    model = ModelType.FN,
                    typeSum = if (pdfNavArgs.type == true) {
                        remoteConfig.getString("sum_fn_15")
                    } else {
                        remoteConfig.getString("sum_fn_36")
                    }
                )
            }

            model.contains("ОФД") -> {
                loadSidorovData(remoteConfig)
                _state.value = currentState.copy(
                    model = ModelType.OFD,
                    typeSum = if (pdfNavArgs.type == true) {
                        remoteConfig.getString("sum_ofd_15")
                    } else {
                        remoteConfig.getString("sum_ofd_36")
                    }
                )
            }

            model.contains("Дримкас Старт") -> getScomSum(remoteConfig, "drimkas_start")
            model.contains("Дримкас учет") -> getScomSum(remoteConfig, "drimkas_uchet")
            model.contains("Дримкас Ключ") -> getScomSum(remoteConfig, "drimkas_kluch")
            model.contains("АТОЛ") -> getScomSum(remoteConfig, "atol")
            model.contains("Сигма") -> getScomSum(remoteConfig, "sigma")
            model.contains("Фронтол") -> getScomSum(remoteConfig, "drimkas_start")
            model.contains("Эвотор") -> getScomSum(remoteConfig, "ewotor")
            model.contains("MTC") -> getScomSum(remoteConfig, "mts")

        }
    }

    fun getScomSum(remoteConfig: FirebaseRemoteConfig, sumtype: String) {
        loadScomData(remoteConfig)
        _state.value = currentState.copy(
            model = ModelType.ELSE,
            typeSum = remoteConfig.getString(sumtype)
        )
    }
}