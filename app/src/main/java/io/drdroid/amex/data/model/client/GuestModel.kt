package io.drdroid.amex.data.model.client

data class GuestModel(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val hasReservation: Boolean,
    var isSelected: Boolean
)
