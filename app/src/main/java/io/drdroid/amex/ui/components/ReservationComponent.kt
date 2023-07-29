package io.drdroid.amex.ui.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.stateDescription
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
    guests: MutableList<GuestModel>?,
    filter: String?,
    onValueChanged: (MutableList<GuestModel>) -> Unit,
    modifier: Modifier
) {
    if (guests == null) {
        //display a loading dialog
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
    } else {
        val groupedGuests = if (filter.isNullOrEmpty()) {
            guests
        } else {
            guests.filter {
                it.firstName.lowercase().contains(filter.lowercase())
                        ||
                        it.lastName.lowercase().contains(filter.lowercase())
            }
        }.groupBy { it.hasReservation }

        LazyColumn(
            modifier = modifier
                .fillMaxHeight(),
//            .clearAndSetSemantics {
//                contentDescription = ""
//                stateDescription = ""
//                customActions= listOf(
//                    CustomAccessibilityAction(
//                        label = "Select/Deselect",
//                        action = { false }
//                    )
//                )
//            }
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
            item {
                InfoSection()
            }
        }
    }

}

@Composable
fun InfoSection() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        CircleText(
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            text = "i",
            backgroundColor = Color(0xFF132C44)
        )
        Spacer(modifier = Modifier.weight(.4f))
        Text(
            modifier = Modifier
                .weight(11f)
                .padding(bottom = 12.dp),
            text = "At least one Guest in the party must have a reservation. Guests without reservations must remain in the same booking party in order to enter.",
            maxLines = 3,
            fontSize = TextUnit(14f, TextUnitType.Sp),
            fontWeight = FontWeight(400),
            letterSpacing = TextUnit(-.28f, TextUnitType.Sp),
            lineHeight = TextUnit(21f, TextUnitType.Sp),
            color = Color(0xFF00233C)
        )
    }
}

@Composable
fun Header(
    text: String,
    modifier: Modifier = Modifier,
    textColor: Color = Color(0xFF00233C),
    backgroundColor: Color = Color.Transparent,
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