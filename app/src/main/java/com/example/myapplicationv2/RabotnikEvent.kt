package com.example.myapplicationv2

sealed interface RabotnikEvent {
    data object SaveRabotnik: RabotnikEvent
    data class SetName(val name: String): RabotnikEvent
    data class SetZarplata(val zarplata: String): RabotnikEvent
    data class SetPosition(val position: String): RabotnikEvent
    data class SetExperience(val experience: String): RabotnikEvent
    data class SetEfficiency(val efficiency: String?): RabotnikEvent
    data object ShowDialog: RabotnikEvent
    data object HideDialog: RabotnikEvent
    data class DeleteRabotnik(val rabotnik: Rabotnik): RabotnikEvent
}