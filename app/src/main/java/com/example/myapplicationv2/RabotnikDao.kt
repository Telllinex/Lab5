package com.example.myapplicationv2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RabotnikDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRabotnik (rabotnik: Rabotnik)
    @Delete
    suspend fun deleteRabotnik (rabotnik: Rabotnik)
    @Query("SELECT * FROM rabotnik")
    fun getAllRabotniks(): Flow<List<Rabotnik>>

}