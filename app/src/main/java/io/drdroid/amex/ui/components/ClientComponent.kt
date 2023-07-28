package io.drdroid.amex.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import io.drdroid.amex.data.model.client.ClientModel

@Composable
fun ClientItem(
    clientModel: ClientModel,
    onClick: () -> Unit
) {
    Row {
        Checkbox(checked = clientModel.isSelected, onCheckedChange = {

        })
    }
}