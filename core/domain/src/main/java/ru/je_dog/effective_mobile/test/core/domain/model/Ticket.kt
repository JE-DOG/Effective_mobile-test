package ru.je_dog.effective_mobile.test.core.domain.model

data class Ticket(
    val id: Int,
    val title: String,
    val timeRange: List<String>,
    val price: Int,
)
