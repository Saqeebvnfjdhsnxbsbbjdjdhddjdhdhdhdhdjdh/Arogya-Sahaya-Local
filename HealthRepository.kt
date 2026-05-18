package com.example.arogyasahaya.repository

import com.example.arogyasahaya.data.HealthDao
import com.example.arogyasahaya.data.MedicationHistory
import com.example.arogyasahaya.data.VitalLog
import kotlinx.coroutines.flow.Flow

class HealthRepository(private val healthDao: HealthDao) {

    val medicationHistory: Flow<List<MedicationHistory>> = healthDao.getMedicationHistory()
    val vitalLogs: Flow<List<VitalLog>> = healthDao.getVitalLogs()

    suspend fun addMedicationLog(medication: MedicationHistory) {
        healthDao.insertMedication(medication)
    }

    suspend fun addVitalLog(vitalLog: VitalLog) {
        healthDao.insertVitalLog(vitalLog)
    }
}
