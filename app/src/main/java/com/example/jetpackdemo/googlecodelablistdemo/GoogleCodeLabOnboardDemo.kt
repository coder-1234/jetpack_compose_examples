package com.example.jetpackdemo.googlecodelablistdemo

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Star


@Composable
fun GoogleCodeLabOnboardDemo(){
    var showList by remember { mutableStateOf(false) }

    if(showList){
        ShowList()
    } else {
        ShowIntro(onContinueClicked = {showList = true})
    }
}

@Composable
fun ShowIntro(onContinueClicked: ()-> Unit){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the Basics Code!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text("Continue")
        }
    }
}

@Composable
fun ShowList(values:List<String> = List(1000){"$it"}){
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(values) {
            Card(
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
            ){
                ShowItem(it)
            }
        }
    }
}

@Composable
fun ShowItem(item:String){
    var expanded by remember { mutableStateOf(false) }
    Row(modifier = Modifier.padding(12.dp).animateContentSize(
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow)
    )) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "Hello, ")
            Text(
                text = item,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4),
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }) {
            Icon(imageVector = if(!expanded) Filled.ArrowDropDown else Filled.Star ,
                contentDescription = "icon")
        }
    }
}