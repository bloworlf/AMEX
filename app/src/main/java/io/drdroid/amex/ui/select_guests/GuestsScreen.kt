package io.drdroid.amex.ui.select_guests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
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
        Box(){

        }
        Reservations(
            modifier = Modifier.weight(1f, fill = true),
            guests = clients,
            onValueChanged = onValueChanged
        )
    }
}