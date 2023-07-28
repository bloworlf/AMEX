package io.drdroid.amex.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import io.drdroid.amex.data.model.client.GuestModel
import java.util.stream.IntStream


@Composable
fun Reservations(
    guests: MutableList<GuestModel>,
    onValueChanged: (MutableList<GuestModel>) -> Unit
) {
    val reservations: List<GuestModel> = guests.filter { it.hasReservation }
    val no_reservation = guests.minus(reservations.toSet())
    val context = LocalContext.current
    Column {
        if (reservations.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
//                    .padding(8.dp)
//                    .weight(4f)
            ) {
                //header
                item {
//                    Surface(shadowElevation = 3.dp, modifier = Modifier.fillMaxWidth()) {
                    Header(text = "These Guests Have Reservations")
//                    }
                }
                //content
                items(reservations) { guest ->
                    GuestItem(guestModel = guest) {
                        val position = IntStream.range(0, guests.size)
                            .filter { i: Int ->
                                guests[i].id == guest.id
                            }
                            .findFirst().orElse(-1)
                        guests[position] = it
                        onValueChanged(guests)
                    }
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(32.dp)
//            .weight(2f)
        )
        if (no_reservation.isNotEmpty()) {

            LazyColumn(
//                modifier = Modifier.weight(4f)
            ) {
                //header
                item {
//                    Surface(shadowElevation = 3.dp, modifier = Modifier.fillMaxWidth()) {
                    Header(text = "These Guests Need Reservations")
//                    }
                }

                //content
                items(no_reservation) { guest ->
                    GuestItem(guestModel = guest) {
                        val position = IntStream.range(0, guests.size)
                            .filter { i: Int ->
                                guests[i].id == guest.id
                            }
                            .findFirst().orElse(-1)
                        guests[position] = it
                        onValueChanged(guests)
                    }
                }
            }
        }
    }
}

@Composable
fun Header(text: String, color: Color = Color(0xFF00233C)) {
    Text(
        text = text,
        fontSize = TextUnit(18f, TextUnitType.Sp),
        fontStyle = FontStyle.Normal,
        color = color,
        letterSpacing = TextUnit(-.36f, TextUnitType.Sp),
        lineHeight = TextUnit(24f, TextUnitType.Sp),
        fontWeight = FontWeight(800),
//        fontFamily = avenir_heavy
    )
}