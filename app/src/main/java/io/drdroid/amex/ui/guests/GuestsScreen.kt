package io.drdroid.amex.ui.guests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.drdroid.amex.data.model.client.GuestModel
import io.drdroid.amex.ui.components.Reservations

@Composable
fun Guests(
    guests: MutableList<GuestModel>?,
    queryText: String?,
    modifier: Modifier,
    onValueChanged: (MutableList<GuestModel>) -> Unit
) {
    Column(
        modifier = modifier
//            .scrollable(
//                state = rememberScrollState(),
//                orientation = Orientation.Vertical
//            )
//            .padding(start = 16.dp, end = 16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Reservations(
            modifier = Modifier.weight(1f, fill = true),
            guests = guests,
            filter = queryText,
            onValueChanged = onValueChanged
        )
    }
}