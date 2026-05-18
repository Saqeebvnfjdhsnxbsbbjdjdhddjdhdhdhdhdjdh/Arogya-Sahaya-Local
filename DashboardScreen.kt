package com.example.arogyasahaya.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
    onNavigateToVitals: () -> Unit,
    onSosTriggered: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
            .padding(16.dp)
    ) {
        Text(
            text = "Arogya-Sahaya",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E88E5), // High contrast blue
            modifier = Modifier.padding(bottom = 24.dp, top = 16.dp)
        )

        // Grid of 4 large touch targets
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DashboardCard(
                    title = "Medical\nProfile",
                    color = Color(0xFF43A047), // High contrast green
                    modifier = Modifier.weight(1f),
                    onClick = { /* TODO: Navigate */ }
                )
                DashboardCard(
                    title = "Pill\nReminder",
                    color = Color(0xFFFDD835), // High contrast yellow
                    textColor = Color.Black,
                    modifier = Modifier.weight(1f),
                    onClick = { /* TODO: Navigate */ }
                )
            }
            
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DashboardCard(
                    title = "ASHA\nConnect",
                    color = Color(0xFF8E24AA), // High contrast purple
                    modifier = Modifier.weight(1f),
                    onClick = { /* TODO: Navigate */ }
                )
                DashboardCard(
                    title = "Vital\nLog",
                    color = Color(0xFF00ACC1), // High contrast cyan
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateToVitals
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Prominent Full-Width SOS Button
        Button(
            onClick = onSosTriggered,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F)), // High contrast red
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp), // Extra large touch target
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = "EMERGENCY SOS",
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    color: Color,
    textColor: Color = Color.White,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .clickable { onClick() },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = color),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = title,
                fontSize = 28.sp, // Large font for elderly
                fontWeight = FontWeight.Bold,
                color = textColor,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                lineHeight = 36.sp
            )
        }
    }
}
