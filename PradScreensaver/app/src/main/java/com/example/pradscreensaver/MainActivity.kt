package com.example.pradscreensaver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
//import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import com.example.pradscreensaver.ui.theme.PradScreensaverTheme
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PradScreensaverTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ClockDisplay()
                }
            }
        }
    }
}

@Composable
fun ClockDisplay() {
    val currentTime = remember { mutableStateOf(getCurrentTime()) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime.value = getCurrentTime()
            kotlinx.coroutines.delay(1000) // Update every second
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black), // Set background color to black
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = currentTime.value,
            fontSize = 70.sp, // Slightly reduced font size
            fontWeight = FontWeight.Bold,
            color = Color.White, // Set text color to white
            modifier = Modifier
                .padding(16.dp) // Add padding
                .fillMaxWidth() // Ensure it fills the width
                .padding(horizontal = 32.dp), // Additional horizontal padding
            maxLines = 1, // Limit to one line
            overflow = TextOverflow.Visible // Change to Visible to avoid ellipses
        )
        Spacer(modifier = Modifier.height(16.dp)) // Space between clock and message
        Text(
            text = "Prady, have a wonderful time!",
            fontSize = 20.sp,
            color = Color.White // Set message color to white
        )
    }
}


fun getCurrentTime(): String {
    val sdf = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
    return sdf.format(Date())
}

@Preview(showBackground = true)
@Composable
fun ClockPreview() {
    PradScreensaverTheme {
        ClockDisplay()
    }
}
