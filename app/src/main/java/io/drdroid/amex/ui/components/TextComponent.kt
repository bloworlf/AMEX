package io.drdroid.amex.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
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
            focusedContainerColor = Color.White,
            focusedTextColor = Color(0xFF132C44),
            unfocusedTextColor = Color(0xFF132C44),
        ),
        value = text.value,
        onValueChange = {
            text.value = it
            onSearchQueryChanged(it)
        },
        label = {
            Text(
                text = "Search",
                letterSpacing = TextUnit(0f, TextUnitType.Sp),
                lineHeight = TextUnit(24f, TextUnitType.Sp),
                color = Color(0xFF132C44),
                fontWeight = FontWeight(400),
            )
        },
        modifier = if (expanded.value) {
            Modifier.fillMaxWidth()
        } else {
            Modifier.width(32.dp)
        }
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            )
            .background(color = Color.Transparent),
        trailingIcon = {
            if (expanded.value) {
                //close btn
                IconButton(
                    modifier = Modifier.background(color = Color.Transparent),
                    onClick = {
                        if (text.value.isNotEmpty()) {
                            text.value = ""
                            onSearchQueryChanged("")
                        } else {
                            expanded.value = !expanded.value
                        }
                    }) {
                    Icon(
                        tint = Color.Blue,
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Clear/close search view"
                    )
                }
            } else {
                //search btn
                keyboardController?.hide()
                IconButton(
                    modifier = Modifier.background(color = Color.Transparent),
                    onClick = { expanded.value = !expanded.value }
                ) {
                    Icon(
                        tint = Color.Blue,
//                        painter = painterResource(id = R.drawable.search),
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Open search view"
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