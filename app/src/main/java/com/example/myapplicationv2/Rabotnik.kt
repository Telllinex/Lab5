package com.example.myapplicationv2


import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "rabotnik")
data class Rabotnik(
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    val name: String,
    val zarplata: Int,
    val position: String

    )


