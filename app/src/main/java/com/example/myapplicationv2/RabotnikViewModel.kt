package com.example.myapplicationv2

import androidx.compose.ui.unit.times
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.times

class RabotnikViewModel(
    private val dao: RabotnikDao
): ViewModel() {
    val _state = MutableStateFlow(RabotnikState())

    init {
        viewModelScope.launch {
            dao.getAllRabotniks().collect { rabotniks ->
                _state.value = _state.value.copy(rabotnik = rabotniks)
            }
        }
    }

    fun onEvent(event: RabotnikEvent) {
        when (event) {
            is RabotnikEvent.DeleteRabotnik -> {
                viewModelScope.launch {
                    dao.deleteRabotnik(event.rabotnik)
                }
            }
            RabotnikEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingRabotnik = false
                    )
                }
            }
            RabotnikEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingRabotnik = true
                    )
                }
            }
            RabotnikEvent.SaveRabotnik -> {
                val name = _state.value.name
                val position = _state.value.position
                val experience = _state.value.experience.toIntOrNull() ?: 0
                val efficiency = _state.value.efficiency.toDoubleOrNull() ?: 0.0
                var zarplata = _state.value.zarplata.toIntOrNull() ?: 0
                if (experience > 5) {
                    zarplata = (zarplata * 1.2 + 500).toInt()
                } else if (experience > 2) {
                    zarplata += 200
                }
                if (position == "Designer" && efficiency != null) {
                    zarplata = (zarplata * efficiency).toInt()
                }
                if (name.isBlank()) {
                    return
                }
                val rabotnik = Rabotnik(
                    name = name,
                    zarplata = zarplata,
                    position = position
                )
                viewModelScope.launch {
                    dao.insertRabotnik(rabotnik)
                }
                _state.update {
                    it.copy(
                        isAddingRabotnik = false,
                        name = "",
                        zarplata = "",
                        position = "Developer",
                        experience = "",
                        efficiency = ""
                    )
                }
            }
            is RabotnikEvent.SetName -> {
                _state.update {
                    it.copy(
                        name = event.name
                    )
                }
            }
            is RabotnikEvent.SetZarplata -> {
                _state.update {
                    it.copy(
                        zarplata = event.zarplata
                    )
                }
            }
            is RabotnikEvent.SetPosition -> {
                _state.update {
                    it.copy(
                        position = event.position
                    )
                }
            }
            is RabotnikEvent.SetExperience -> {
                _state.update {
                    it.copy(
                        experience = event.experience
                    )
                }
            }
            is RabotnikEvent.SetEfficiency -> {
                _state.update {
                    it.copy(
                        efficiency = event.efficiency ?: ""
                    )
                }
            }
        }
    }
}
