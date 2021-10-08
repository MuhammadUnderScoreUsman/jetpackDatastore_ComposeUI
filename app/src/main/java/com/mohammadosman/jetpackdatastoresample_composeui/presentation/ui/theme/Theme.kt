package com.mohammadosman.jetpackdatastoresample_composeui.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

val lightThemeColor = lightColors(
    primary = primaryC,
    primaryVariant = primaryLightC,
    secondary = secondaryT,
    secondaryVariant = secondaryLightT
)


@Composable
fun TaskTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        content = content,
        colors = lightThemeColor
    )

}