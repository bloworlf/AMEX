package io.drdroid.amex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CircleText(modifier: Modifier, text: String, backgroundColor: Color) {
    Text(
        color = Color.White,
        modifier = modifier
            .background(color = backgroundColor, shape = CircleShape)
//            .aspectRatio(1f)
//            .circleLayout()
//            .padding(8.dp)
        ,
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleSmall
    )
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    keyboardController: SoftwareKeyboardController?,
    onSearchQueryChanged: (String) -> Unit
) {
    val expanded = remember {
        mutableStateOf(false)
    }
    val text = remember {
        mutableStateOf("")
    }

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = if (expanded.value) {
                Color.White
            } else {
                Color.Transparent
            },
            focusedContainerColor = Color.White
        ),
        value = text.value,
        onValueChange = {
            text.value = it
            onSearchQueryChanged(it)
        },
        label = {
            Text("Search")
        },
        modifier = if (expanded.value) {
            Modifier.fillMaxWidth()
        } else {
            Modifier.width(32.dp)
        }
            .background(color = Color.Transparent),
        trailingIcon = {
            if (expanded.value) {
                //close btn
                IconButton(onClick = {
                    if (text.value.isNotEmpty()) {
                        text.value = ""
                        onSearchQueryChanged("")
                    } else {
                        expanded.value = !expanded.value
                    }
                }) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = ""
                    )
                }
            } else {
                //search btn
                keyboardController?.hide()
                IconButton(onClick = { expanded.value = !expanded.value }) {
                    Icon(
//                        painter = painterResource(id = R.drawable.search),
                        imageVector = Icons.Filled.Search,
                        contentDescription = ""
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
            //or hide keyboard
            focusManager.clearFocus()
        })
    )
}