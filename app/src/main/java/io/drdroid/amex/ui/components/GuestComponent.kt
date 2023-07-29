package io.drdroid.amex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import io.drdroid.amex.data.model.client.GuestModel

@Composable
fun GuestItem(
    guestModel: GuestModel,
    onSelected: (GuestModel) -> Unit
) {
    val checked = remember {
        mutableStateOf(guestModel.isSelected)
    }
    Row(
        modifier = Modifier
            .background(Color.White)
            .padding(start = 16.dp, end = 16.dp)
            .clickable {
                checked.value = !checked.value
                guestModel.isSelected = !guestModel.isSelected
                onSelected(guestModel)
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            modifier = Modifier
//                .size(width = 16.dp, height = 16.dp)
//                .background(Color.White)
                .weight(1f),
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
                guestModel.isSelected = it
                onSelected(guestModel)
            }
        )

        Text(
            modifier = Modifier
                .weight(6f)
                .padding(
                    start = 16.dp, top = 10.dp, bottom = 10.dp
                ),
            text = "${guestModel.firstName} ${guestModel.lastName}",
            letterSpacing = TextUnit(0f, TextUnitType.Sp),
            lineHeight = TextUnit(24f, TextUnitType.Sp),
            color = Color(0xFF132C44),
            fontWeight = FontWeight(400)
        )
    }
}