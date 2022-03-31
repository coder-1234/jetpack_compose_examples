package com.example.jetpackdemo.statemaintaindemo

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StateMaintainingDemo(){

    var nameState by rememberSaveable{
        mutableStateOf("")
    }
    var name by rememberSaveable{
        mutableStateOf("")
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center){

        Text(text = "Hello $name",color = MaterialTheme.colors.primaryVariant,
            style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(20.dp))
        TextField(value = nameState, onValueChange = {
            nameState = it
        })
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { name = nameState }) {
            Text(text = "Display")
        }
    }
}