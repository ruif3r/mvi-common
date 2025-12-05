package com.ruif3r.sample.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import com.ruif3r.sample.R

@Composable
fun SampleButton(shouldShow: Boolean, onShowClick: () -> Unit, onNavigateButton: () -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.align(Alignment.Center)) {
            Button(
                onClick = onShowClick,
            ) {
                Text(if (shouldShow) "Close" else "Show")
            }
            AnimatedVisibility(shouldShow) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color.Green)
                )
                Button(
                    onClick = onNavigateButton,
                ) {
                    Text("Navigate")
                }
            }
        }
    }
}