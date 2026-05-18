package com.example.arogyasahaya

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.arogyasahaya.ui.DashboardScreen
import com.example.arogyasahaya.ui.VitalsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArogyaSahayaApp(
                        onSosTriggered = {
                            Toast.makeText(this, "SOS TRIGGERED! Contacting Emergency Services...", Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ArogyaSahayaApp(onSosTriggered: () -> Unit) {
    var currentScreen by remember { mutableStateOf("Dashboard") }

    when (currentScreen) {
        "Dashboard" -> DashboardScreen(
            onNavigateToVitals = { currentScreen = "Vitals" },
            onSosTriggered = onSosTriggered
        )
        "Vitals" -> VitalsScreen()
    }
}
