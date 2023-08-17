package ru.kassi.onlinekassa.extentions

object JsonValidation {

    fun<T> validateAndGet(tryGet: () -> T?): Pair<Boolean, T?> {

        return try {
            true to tryGet()
        } catch (e: Exception) {
            false to null
        }
    }
}