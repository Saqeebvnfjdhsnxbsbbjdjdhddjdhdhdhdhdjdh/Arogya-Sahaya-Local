package com.mindmatrix.arogyasahaya.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "medication")
data class Medication(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val dosage: String,
    val timeOfDay: String
)

@Entity(tableName = "vital_logs")
data class VitalLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: Long,
    val bloodPressure: String,
    val heartRate: Int
)

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medication")
    fun getAllMedications(): Flow<List<Medication>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedication(medication: Medication)

    @Delete
    suspend fun deleteMedication(medication: Medication)
}

@Dao
interface VitalLogDao {
    @Query("SELECT * FROM vital_logs ORDER BY date DESC LIMIT 7")
    fun getLastSevenVitalLogs(): Flow<List<VitalLog>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVitalLog(vitalLog: VitalLog)
}

@Database(entities = [Medication::class, VitalLog::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicationDao(): MedicationDao
    abstract fun vitalLogDao(): VitalLogDao
}
