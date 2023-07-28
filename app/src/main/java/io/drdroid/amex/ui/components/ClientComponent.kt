package io.drdroid.amex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import io.drdroid.amex.data.model.client.ClientModel

@Composable
fun ClientItem(
    clientModel: ClientModel,
    onClick: () -> Unit
) {
    val checked = remember {
        mutableStateOf(clientModel.isSelected)
    }
    Row(
        modifier = Modifier.clickable {
            checked.value = !checked.value
        }
    ) {
        Checkbox(
            modifier = Modifier
                .size(width = 20.dp, height = 20.dp)
                .background(Color.White)
                .weight(1f),
            checked = checked.value,
            onCheckedChange = {
                checked.value = it
                clientModel.isSelected = it
            }
        )

        Text(
            text = "${clientModel.firstName} ${clientModel.lastName}",
            letterSpacing = TextUnit(0f, TextUnitType.Sp),
            lineHeight = TextUnit(24f, TextUnitType.Em),
            color = Color(0x132C44),
            fontWeight = FontWeight(400)
        )
    }
}