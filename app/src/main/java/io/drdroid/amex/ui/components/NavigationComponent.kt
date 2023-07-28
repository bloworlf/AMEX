package io.drdroid.amex.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.drdroid.amex.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onNavigationIconClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    TopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF00233C),
                textAlign = TextAlign.Center
            )
        },
        navigationIcon = {
            Icon(
                modifier = Modifier
//                    .fillMaxHeight()
                    .padding(12.dp)
                    .clickable {
                        onNavigationIconClick()
                    },
                tint = Color.Blue,
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = ""
            )
        },
//        navigationIcon = {
//            IconButton(onClick = onNavigationIconClick) {
//                Icon(
//                    tint = Color.Blue,
//                    imageVector = ImageVector.vectorResource(id = R.drawable.arrow_back), // Replace with your vector drawable
//                    contentDescription = "Menu Icon"
//                )
//            }
//        },
//        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(),
        scrollBehavior = scrollBehavior,
        modifier = Modifier
//            .verticalScroll(scrollState)
            .background(color = Color.White),
//        colors = TopAppBarColors(
//            containerColor = Color.White,
//            scrolledContainerColor = Color.White,
//            navigationIconContentColor = Color.White,
//            titleContentColor = Color.White,
//            actionIconContentColor = Color.White,
//            ),
//        elevation = AppBarDefaults.TopAppBarElevation
    )
}