package com.example.week3_bai1

//import androidx.compose.runtime.remember
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.week3_bai1.ui.theme.NiconneFontFamily
import com.example.week3_bai1.ui.theme.Week3_Bai1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Week3_Bai1Theme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

enum class Screen {
    ListScreen,
    TextDetail
}

@Composable
fun MyApp(modifier: Modifier = Modifier) {

    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    var currentScreen by rememberSaveable { mutableStateOf(Screen.ListScreen) }

    Surface (modifier) {
        if (shouldShowOnboarding) {
            OnboardingScreen(onContinueClicked = { shouldShowOnboarding = false })
        } else {
            when (currentScreen) {
                Screen.ListScreen -> {
                    ListScreen(onClicked = {
                        currentScreen = Screen.TextDetail
                    }
                    )
                }
                    Screen.TextDetail -> {
                        TextDetailScreen(
                            onBackClicked = {currentScreen = Screen.ListScreen}
                        )
                }
            }
        }
    }
}

@Composable
fun OnboardingScreen(
    onContinueClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(top = 128.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val painter = painterResource(id = R.drawable.logo)
        Image(
            painter = painter,
            contentDescription = "My Image",
            modifier = Modifier
                .size(250.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column( horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()) {
            Text("Jetpack Compose",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { heading() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Jetpack Compose is a modern UI toolkit for building native Android applications using a declarative programming approach.",
                style = MaterialTheme.typography.bodyMedium.copy(
                    color = Color(0xFF4A4646),
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.8F)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            modifier = Modifier
                .padding(vertical = 45.dp)
                .height(60.dp)
                .width(300.dp),
            onClick = onContinueClicked,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2196F3), // Màu nền của nút
                contentColor = White // Màu chữ của nút
            )
        ) {
            Text("I'm ready")
        }
    }
}

@Composable
fun ListScreen(
    modifier: Modifier = Modifier,
    onClicked: () -> Unit
) {
    Column(modifier = modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "UI Components List", style = MaterialTheme.typography.headlineMedium.copy(
                color = Color(0xFF2196F3),
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier
                .padding(top = 60.dp, bottom = 10.dp)
        )
        LazyColumn {
            item {
                SectionTitle(title = "Display")
                ComponentCard(
                    name = "Text",
                    description = "Displays text",
                    onClick = {
                    onClicked()
                })
                ComponentCard(name = "Image", description = "Displays an image", onClick = {
                    onClicked()
                })
            }

            // Danh mục "Input"
            item {
                SectionTitle(title = "Input")
                ComponentCard(name = "TextField", description = "Input field for text", onClick = {
                    onClicked()
                })
                ComponentCard(
                    name = "PasswordField",
                    description = "Input field for passwords",
                    onClick = {
                        onClicked()
                    })
            }

            // Danh mục "Layout"
            item {
                SectionTitle(title = "Layout")
                ComponentCard(name = "Column", description = "Arranges elements vertically", onClick = {
                    onClicked()
                })
                ComponentCard(name = "Row", description = "Arranges elements horizontally", onClick = {
                    onClicked()
                })
            }
        }
    }


    }

@Composable
fun ComponentCard(name: String, description: String, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFbce0fb)
        ),
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 10.dp, bottom = 10.dp, end = 10.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        )
    {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(text = description, fontSize = 14.sp)
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        modifier = Modifier
            .padding(top = 20.dp, bottom = 4.dp, start = 10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(
    onBackClicked: () -> Unit )
{
    BackHandler(onBack = onBackClicked)
    val brownColor = Color(0xFFB36C00)
    val fancyText = buildAnnotatedString {
        // Dòng 1: "The quick Brown"
        append("The ")

        // "quick" (gạch ngang)
        withStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.LineThrough
            )
        ) {
            append("quick")
        }
        append(" ")

        // "Brown" (màu nâu/cam, đậm, to hơn)
        withStyle(
            style = SpanStyle(
                color = brownColor,
                fontWeight = FontWeight.SemiBold,
                fontSize = 50.sp
            )
        ) {
            append("B")
        }
        withStyle(
            style = SpanStyle(
                color = brownColor,
                fontWeight = FontWeight.Normal,
                fontSize = 40.sp
            )
        ) {
            append("rown")
        }

        append("\n") // xuống dòng

        // Dòng 2: "fox jumps over"
        append("fox j u m p s ")

        // "over" (in đậm, nghiêng)
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic
            )
        ) {
            append("over")
        }

        append("\n") // xuống dòng

        // Dòng 3: "the lazy dog."
        withStyle(
            style = SpanStyle(
                fontStyle = FontStyle.Italic,
                textDecoration = TextDecoration.Underline
                )
        ) {
            append("the")
        }
        // "lazy" (nghiêng + gạch chân)
        withStyle(
            style = SpanStyle(
                fontFamily = NiconneFontFamily,
                fontStyle = FontStyle.Italic,
                fontSize = 40.sp
            )
        ) {
            append(" lazy")
        }
        append(" dog.")
    }

    // Bố cục tổng thể
    Scaffold(
        topBar = {
            // Thanh tiêu đề "Text Detail" (ở giữa)
            CenterAlignedTopAppBar(
                title = { Text("Text Detail",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF2196F3),
                    ),
                    ) },
                navigationIcon = {
                    IconButton(onClick = onBackClicked) { // Thêm nút back
                        Icon(
                            Icons.Default.ArrowBackIosNew, // Thêm icon back
                            contentDescription = "Back",
                            tint = Color(0xFF2196F3)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        // Nội dung màn hình
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Hiển thị đoạn text “trộn style”
            Text(
                text = fancyText,
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 40.sp,
                    lineHeight = 50.sp
                ),
            )

            // Đường kẻ ngang ngay dưới đoạn text
            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth(),
                thickness = 1.dp,
                color = Color.LightGray
            )
        }
    }
}



@Preview(showBackground = true, widthDp = 400)
@Composable
fun TextDetailScreenPreview() {
    Week3_Bai1Theme {
        TextDetailScreen(onBackClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun OnboardingPreview() {
    Week3_Bai1Theme {
        OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun ListSceenPreview() {
    Week3_Bai1Theme {
        ListScreen(onClicked = {}, modifier = Modifier.fillMaxSize())
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun MyAppPreview() {
    Week3_Bai1Theme {
        MyApp(Modifier.fillMaxSize())
    }
}
