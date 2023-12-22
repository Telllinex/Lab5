package com.example.myapplicationv2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.myapplicationv2.ui.theme.MyApplicationV2Theme
@Suppress("UNCHECKED_CAST")

class MainActivity : ComponentActivity() {
    private val db by lazy {
        Room.databaseBuilder(
            applicationContext,
            RabotnikDatabase::class.java,
            "rabotnik.db"
        ).build()
    }
    private val viewModel by viewModels<RabotnikViewModel>(
        factoryProducer = {
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return RabotnikViewModel(db.dao) as T
                }
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationV2Theme {
                val state by viewModel._state.collectAsState()
                RabotnikScreen(state = state, onEvent = viewModel::onEvent)
            }
        }
    }
}