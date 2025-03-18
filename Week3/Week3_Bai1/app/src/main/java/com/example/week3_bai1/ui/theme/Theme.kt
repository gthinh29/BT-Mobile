package com.example.week3_bai1.ui.theme


import android.app.Activity
import android.os.Build
import android.view.View
import android.view.WindowInsetsController
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    surface = Blue,
    onSurface = Navy,
    primary = Navy,
    onPrimary = Chartreuse
)

private val LightColorScheme = lightColorScheme(
    surface = Blue,
    onSurface = Color.White,
    primary = LightBlue,
    onPrimary = Navy
)

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */


@Composable
fun Week3_Bai1Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
      dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
        val context = LocalContext.current
        if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
      }
      darkTheme -> DarkColorScheme
      else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {

        SideEffect {
            val window = (view.context as Activity).window

            // ✅ Hỗ trợ toàn màn hình (giữ tương thích với Android 11+)
            WindowCompat.setDecorFitsSystemWindows(window, false)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {  // ✅ API 30+ (Android 11+)
                val insetsController = window.insetsController

                // ✅ Thay đổi màu icon trên thanh trạng thái (Android 11+)
                insetsController?.setSystemBarsAppearance(
                    if (darkTheme) 0 else WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                @Suppress("DEPRECATION") // ✅ Bỏ qua cảnh báo lỗi API thấp hơn
                window.decorView.systemUiVisibility = if (darkTheme) {
                    0 // Chế độ tối -> Icon trắng
                } else {
                    View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR // Chế độ sáng -> Icon tối
                }
            }

            // ✅ Cách thay đổi màu thanh trạng thái an toàn (hỗ trợ mọi phiên bản Android)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {  // ✅ Chỉ dùng `statusBarColor` nếu API < 31
                @Suppress("DEPRECATION")
                window.statusBarColor = colorScheme.primary.toArgb()
            }
        }
    }

    MaterialTheme(
      colorScheme = colorScheme,
      typography = Typography,
      content = content
    )
}