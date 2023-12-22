package com.example.myapplicationv2

data class RabotnikState(
    val rabotnik: List<Rabotnik> = emptyList(),
    val name: String = "",
    val zarplata: String = "",
    val position: String = "Developer",
    val experience: String = "",
    val efficiency: String = "",
    val isAddingRabotnik: Boolean = false, // Add new Rabotnik
)
