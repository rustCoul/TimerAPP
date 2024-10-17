package com.example.timerapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TimerViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    var time=0
    suspend fun LaunchEffect(){
        while(_uiState.value.IsRunning){

             if(time == 0 ){break}

            delay(1000)
            time= _uiState.value.time.minus(1)

            _uiState.value =
                _uiState.value.copy(
                time=time
                )

        }
    }
   suspend fun updateState( State:Boolean){
        _uiState.update { currentState ->
            currentState.copy(
                IsRunning = State
            )
        }
        LaunchEffect()
    }
    fun UpdateTime(i:Int){
        _uiState.update { currentState ->
            currentState.copy(time = i)
        }
    }
    fun resetGame() {

        _uiState.value = UiState(time = 0, IsRunning = false)
    }
}