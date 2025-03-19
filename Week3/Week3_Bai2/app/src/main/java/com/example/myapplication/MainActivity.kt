package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                OnboardingFlow()
            }
        }
    }
}

@Composable
fun OnboardingFlow() {
    // 0: Splash, 1: Onboarding Page 1, 2: Onboarding Page 2, 3: Onboarding Page 3
    var currentPage by rememberSaveable {mutableIntStateOf(0)}

    // Splash Screen tự động chuyển sau 3 giây
    if (currentPage == 0) {
        LaunchedEffect(Unit) {
            delay(3000)
            currentPage = 1
        }
        SplashScreen()
    } else {
        // Hiển thị từng trang Onboarding
        Box(modifier = Modifier.fillMaxSize()) {
            when (currentPage) {
                1 -> OnboardingPage(
                    imageRes = R.drawable.easy_time_management,
                    title = "Easy Time Management",
                    description = "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first."
                )
                2 -> OnboardingPage(
                    imageRes = R.drawable.increase_work_effectiveness,
                    title = "Increase Work Effectiveness",
                    description = "Time management and the determination of more important tasks will give your job statistics better and always improve."
                )
                3 -> OnboardingPage(
                    imageRes = R.drawable.reminder_notification,
                    title = "Reminder Notification",
                    description = "The advantage of this application is that it also provides reminders so you don’t forget to keep doing your assignments well and according to the time you have set.",
                    isLastPage = true
                )
            }
            // Thanh điều hướng dưới cùng
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                if (currentPage == 1) {
                    // --- TRANG ĐẦU: Nút Next “tràn full” ---
                    Button(
                        onClick = { currentPage++ },
                        shape = RoundedCornerShape(50),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(
                            text = "Next",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                } else {
                    // --- CÁC TRANG SAU (page = 2, 3): Nút Back + Nút Next/Get Started ---
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        // Nút Back (chỉ hiển thị nếu currentPage > 1)
                        IconButton(
                            onClick = { currentPage-- },
                            modifier = Modifier
                                .background(Color(0xFF2196F3), shape = CircleShape)
                                .size(50.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White,
                                modifier = Modifier.size(45.dp),
                            )
                        }

                        // Nút Next / Get Started
                        Button(
                            onClick = {
                                if (currentPage < 3) {
                                    currentPage++
                                } else {
                                    // Trang cuối => chuyển sang Main Screen
                                    println("Navigate to Main Screen")
                                }
                            },
                            shape = RoundedCornerShape(50),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                            modifier = Modifier
                                .width(250.dp)
                                .height(50.dp)
                        ) {
                            Text(
                                text = if (currentPage < 3) "Next" else "Get Started",
                                color = Color.White,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {
    // Splash Screen hiển thị logo và tên ứng dụng
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.uthlogo),
            contentDescription = "UTH Logo",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "UTH SmartTasks",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2196F3),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun OnboardingPage(
    imageRes: Int,
    title: String,
    description: String,
    isLastPage: Boolean = false
) {
    // Màn hình Onboarding hiển thị nội dung theo từng trang
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = title,
            modifier = Modifier.size(250.dp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = description,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center
        )
        // Nút điều hướng (Next/Get Started) được đặt ở dưới cùng, nhưng trong flow này
        // chúng ta đã đặt nút điều hướng chung ở Box bên ngoài để dễ kiểm soát Back/Next
        if (isLastPage) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "This is the last page",
                fontSize = 14.sp,
                color = Color.Gray
            )
        }
    }
}
