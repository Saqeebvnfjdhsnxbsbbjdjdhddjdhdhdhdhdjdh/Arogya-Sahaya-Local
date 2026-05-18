package com.example.arogyasahaya.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

@Composable
fun VitalsScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Vital Log",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp, top = 16.dp)
        )

        Text(
            text = "7-Day Blood Pressure Trend",
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // MPAndroidChart in Compose using AndroidView
        AndroidView(
            factory = { context ->
                LineChart(context).apply {
                    description.isEnabled = false
                    setTouchEnabled(true)
                    isDragEnabled = true
                    setScaleEnabled(true)
                    setPinchZoom(true)

                    xAxis.position = XAxis.XAxisPosition.BOTTOM
                    xAxis.setDrawGridLines(false)

                    axisRight.isEnabled = false

                    // Dummy Data for demonstration
                    val systolicEntries = listOf(
                        Entry(1f, 120f), Entry(2f, 122f), Entry(3f, 118f),
                        Entry(4f, 125f), Entry(5f, 130f), Entry(6f, 128f), Entry(7f, 121f)
                    )
                    
                    val diastolicEntries = listOf(
                        Entry(1f, 80f), Entry(2f, 82f), Entry(3f, 79f),
                        Entry(4f, 85f), Entry(5f, 88f), Entry(6f, 86f), Entry(7f, 81f)
                    )

                    val sysDataSet = LineDataSet(systolicEntries, "Systolic").apply {
                        color = android.graphics.Color.RED
                        setCircleColor(android.graphics.Color.RED)
                        lineWidth = 3f
                        circleRadius = 5f
                        valueTextSize = 14f // Large font
                    }
                    
                    val diaDataSet = LineDataSet(diastolicEntries, "Diastolic").apply {
                        color = android.graphics.Color.BLUE
                        setCircleColor(android.graphics.Color.BLUE)
                        lineWidth = 3f
                        circleRadius = 5f
                        valueTextSize = 14f // Large font
                    }

                    data = LineData(sysDataSet, diaDataSet)
                    invalidate() // refresh
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )
    }
    // Ensure you are using viewModelScope to launch on the IO thread
    fun fetchVitals() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repository.getLastSevenDaysOfVitals()
            // Switch back to Main thread to update the UI
            withContext(Dispatchers.Main) {
                _vitalsState.value = data
            }
        }
    }
    if (vitalsList.isEmpty()) {
        // Show a high-contrast message instead of a crashing graph
        Text(
            text = "No vitals logged yet. Add your BP/Heart Rate above.",
            fontSize = 22.sp,
            color = Color.Black
        )
    } else {
        // ONLY show the graph if there is data
        AndroidView(
            factory = { context ->
                LineChart(context).apply {
                    // Initial chart setup here
                }
            },
            update = { chart ->
                // Update chart data here safely
            }
        )
    }
    val vitals by viewModel.vitalsList.collectAsState()

    if (vitals.isEmpty()) {
        // Show accessibility-friendly message instead of crashing chart
        Text(
            text = "No vitals recorded for the last 7 days.",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )
    } else {
        // Show the Line Chart only when data is present
        AndroidView(
            factory = { context -> LineChart(context).apply { /* setup */ } },
            update = { chart -> /* update data */ }
        )
    }
}
