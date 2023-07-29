package io.drdroid.amex.ui.select_guests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import io.drdroid.amex.data.model.client.GuestModel
import io.drdroid.amex.ui.components.CircleText
import io.drdroid.amex.ui.components.Reservations

@Composable
fun Guests(
    clients: MutableList<GuestModel>,
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
            modifier = Modifier.weight(1f),
            guests = clients,
            onValueChanged = onValueChanged
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            CircleText(
                modifier = Modifier
//                    .size(24.dp)
//                    .width(18.dp)
                    .weight(2f),
                text = "i",
                backgroundColor = Color(0xFF132C44)
            )
            Spacer(modifier = Modifier.weight(.4f))
            Text(
                modifier = Modifier.weight(6f),
                text = "At least one Guest in the party must have a reservation. Guests without reservations must remain in the same booking party in order to enter.",
                maxLines = 3,
                fontSize = TextUnit(14f, TextUnitType.Sp)
            )
        }
    }
}