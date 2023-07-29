package io.drdroid.amex.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import io.drdroid.amex.data.model.client.GuestModel
import java.util.stream.IntStream


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Reservations(
    guests: MutableList<GuestModel>,
    onValueChanged: (MutableList<GuestModel>) -> Unit,
    modifier: Modifier
) {
    val groupedGuests = guests.groupBy { it.hasReservation }
    LazyColumn(
        modifier = modifier.fillMaxHeight(),

        ) {
        groupedGuests.forEach { (hasReservation, rGuests) ->
            stickyHeader {
                Header(
                    modifier = Modifier.fillMaxWidth(),
                    text = if (hasReservation) {
                        "These Guests Have Reservations"
                    } else {
                        "These Guests Need Reservations"
                    },
                    backgroundColor = Color.White
                )
            }
            items(rGuests) { guest ->
                // Display each guest as a list item
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

@Composable
fun Header(
    text: String,
    textColor: Color = Color(0xFF00233C),
    backgroundColor: Color = Color.Transparent,
    modifier: Modifier = Modifier
) {
    Surface(
        color = backgroundColor,
//        shadowElevation = .5.dp
    ) {
        Box(
            modifier = modifier
                .height(64.dp)
                .background(backgroundColor)
                .statusBarsPadding()
                .navigationBarsPadding(),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = text,
                fontSize = TextUnit(18f, TextUnitType.Sp),
                fontStyle = FontStyle.Normal,
                color = textColor,
                letterSpacing = TextUnit(-.36f, TextUnitType.Sp),
                lineHeight = TextUnit(24f, TextUnitType.Sp),
                fontWeight = FontWeight(800),
            )
        }
    }
}