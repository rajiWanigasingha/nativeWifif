package org.wifi.project.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import org.jetbrains.compose.resources.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import nativewifi.composeapp.generated.resources.JetBrainsMono_Bold
import nativewifi.composeapp.generated.resources.JetBrainsMono_ExtraBold
import nativewifi.composeapp.generated.resources.JetBrainsMono_Medium
import nativewifi.composeapp.generated.resources.JetBrainsMono_Regular
import nativewifi.composeapp.generated.resources.JetBrainsMono_SemiBold
import nativewifi.composeapp.generated.resources.JetBrainsMono_Thin
import nativewifi.composeapp.generated.resources.Res

@Composable
fun JetBrainsFontThin(color: Color, fontSize: TextUnit = 13.sp) = TextStyle(
        fontFamily = FontFamily(Font(Res.font.JetBrainsMono_Thin)),
        fontWeight = FontWeight.Normal,
        fontSize = fontSize,
        color = color
    )

@Composable
fun JetBrainsFontRegular(color: Color, fontSize: TextUnit = 14.sp) = TextStyle(
    fontFamily = FontFamily(Font(Res.font.JetBrainsMono_Regular)),
    fontWeight = FontWeight.Normal,
    fontSize = fontSize,
    color = color
)

@Composable
fun JetBrainsFontMedium(color: Color, fontSize: TextUnit = 16.sp) = TextStyle(
    fontFamily = FontFamily(Font(Res.font.JetBrainsMono_Medium)),
    fontWeight = FontWeight.Medium,
    fontSize = fontSize,
    color = color
)


@Composable
fun JetBrainsFontSemiBold(color: Color, fontSize: TextUnit = 18.sp) = TextStyle(
    fontFamily = FontFamily(Font(Res.font.JetBrainsMono_SemiBold)),
    fontWeight = FontWeight.SemiBold,
    fontSize = fontSize,
    color = color
)

@Composable
fun JetBrainsFontBold(color: Color, fontSize: TextUnit = 24.sp) = TextStyle(
    fontFamily = FontFamily(Font(Res.font.JetBrainsMono_Bold)),
    fontWeight = FontWeight.Bold,
    fontSize = fontSize,
    color = color
)

@Composable
fun JetBrainsFontExtraBold(color: Color, fontSize: TextUnit = 30.sp) = TextStyle(
    fontFamily = FontFamily(Font(Res.font.JetBrainsMono_ExtraBold)),
    fontWeight = FontWeight.ExtraBold,
    fontSize = fontSize,
    color = color
)