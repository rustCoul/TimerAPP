package com.example.timerapp

import com.example.timerapp.TimerViewModel
import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.timerapp.ui.theme.TimerAppTheme


@Composable
fun TimerLayout(timerViewModel:TimerViewModel=viewModel(),paddingValues: PaddingValues) {
    val uiState by timerViewModel.uiState.collectAsState()
    var seconds by remember{
        mutableIntStateOf(0)
    }
    Column( horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(paddingValues)){

        Text(text=uiState.time.toString() ,fontSize = 45.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally))
        OutlinedTextField(value = seconds.toString(),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {seconds=it.toInt()},
            label = {

                    Text(text="Enter seconds")
                },

            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Row {
            Button(modifier = Modifier, onClick = {timerViewModel.UpdateTime(seconds);suspend { timerViewModel.updateState(false)} }) {
                Text(text="Start")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { suspend { timerViewModel.updateState(false)}}) {
                Text(text="Pause")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(onClick = { timerViewModel.resetGame()}) {
                Text(text="Reset")
            }
        }

    }
}
@Preview(showBackground = true)
@Composable
fun GameScreenPreview() {
  TimerAppTheme {
      TimerLayout(paddingValues = PaddingValues(16.dp))
  }
}