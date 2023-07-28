package io.drdroid.amex.data.model.client

data class ClientModel(
    val firstName: String,
    val lastName: String,
    val hasReservation: Boolean,
    var isSelected: Boolean
)
