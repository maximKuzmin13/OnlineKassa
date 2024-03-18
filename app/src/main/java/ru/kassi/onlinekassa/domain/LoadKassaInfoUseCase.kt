package ru.kassi.onlinekassa.domain

import ru.kassi.onlinekassa.domain.api.kassa.KassaData
import ru.kassi.onlinekassa.domain.api.kassa.KassaRepository
import javax.inject.Inject

class LoadKassaInfoUseCase @Inject constructor(
    private val kassaRepository: KassaRepository,
) {

    suspend fun loadKassaInfo(num: String): List<KassaData> {
        val kassa = kassaRepository.getKassaInfo(num).response
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
        return list.toList()
    }
}