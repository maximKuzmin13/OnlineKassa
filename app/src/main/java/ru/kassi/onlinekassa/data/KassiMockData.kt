package ru.kassi.onlinekassa.data

object KassiMockData {

    val data = listOf<Kassa>(
        Kassa(
            title = "Мир Цветов",
            adress = "Москва, Гончарный проезд 8/40",
            kassa = "Эвотор",
            term = "29.10.2023"
        ),
        Kassa(
            title = "Мир Цветов",
            adress = "Москва, Гончарный проезд 8/40",
            kassa = "Эвотор",
            term = "30.05.2024"
        ),
        Kassa(
            title = "Типография",
            adress = "Москва, Гончарный проезд 8/40",
            kassa = "МТС",
            term = "30.08.2023"
        ),
        Kassa(
            title = "Продукты",
            adress = "Москва, Гончарный проезд 8/40",
            kassa = "МТС",
            term = "На регистрации"
        ),
    )
}