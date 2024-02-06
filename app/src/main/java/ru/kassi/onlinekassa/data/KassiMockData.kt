package ru.kassi.onlinekassa.data

object KassiMockData {

    val data = listOf<Kassa>(
        Kassa(
            title = "Мир Цветов",
            address = "Москва, Гончарный проезд 8/40",
            kassa = "Эвотор",
            serviceName = "ОФД 1",
            term = "29.10.2024"
        ),
        Kassa(
            title = "Мир Цветов",
            address = "Москва, Гончарный проезд 8/40",
            kassa = "Эвотор",
            serviceName = "ОФД 2",
            term = "30.05.2024"
        ),
        Kassa(
            title = "Типография",
            address = "Москва, Гончарный проезд 8/40",
            kassa = "МТС",
            serviceName = "ОФД 3",
            term = "30.08.2023"
        ),
        Kassa(
            title = "Продукты",
            address = "Москва, Гончарный проезд 8/40",
            kassa = "МТС",
            serviceName = "ОФД 4",
            term = "На регистрации"
        ),
    )
}