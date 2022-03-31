package com.example.jetpackdemo.profilepage

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.jetpackdemo.R

@Composable
fun SimpleProfilePage(){
    Card(elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 96.dp)
            .border(width = 2.dp, color = Color.Magenta, shape = RoundedCornerShape(30.dp))){

        Column(horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.verticalScroll(rememberScrollState())) {
            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(200.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.Red, shape = CircleShape),
                contentScale = ContentScale.Crop
            )

            Text(text="Username", fontWeight = FontWeight.Bold)

            Text(text="Name")

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "T1", fontWeight = FontWeight.Bold)
                    Text(text = "Sub T1")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "T1", fontWeight = FontWeight.Bold)
                    Text(text = "Sub T1")
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "T1", fontWeight = FontWeight.Bold)
                    Text(text = "Sub T1")
                }
            }

            Row(horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)) {
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button1", fontSize = 16.sp)
                }
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Button2", fontSize = 16.sp)
                }
            }
            
        }
    }
}

@Composable
fun ConstraintProfilePage(){
    val config = LocalConfiguration.current
    if(config.orientation == Configuration.ORIENTATION_LANDSCAPE){
        Card(elevation = 6.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 48.dp)
                .border(width = 2.dp, color = Color.Magenta, shape = RoundedCornerShape(30.dp))){
            ConstrainLayout()
        }
    }
    else{
        Card(elevation = 6.dp,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp, vertical = 96.dp)
                .border(width = 2.dp, color = Color.Magenta, shape = RoundedCornerShape(30.dp))){
            ConstrainLayout()
        }
    }
}

@Composable
private fun ConstrainLayout(){
    ConstraintLayout(modifier = Modifier.verticalScroll(rememberScrollState())) {

        val (image, username, name, row, btn1, btn2) = createRefs()

        val guideline = createGuidelineFromTop(0.1f)

        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile Picture",
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
                .border(2.dp, Color.Red, shape = CircleShape)
                .constrainAs(image) {
                    top.linkTo(guideline)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
            contentScale = ContentScale.Crop
        )

        Text(text="Username", fontWeight = FontWeight.Bold,
            modifier = Modifier.constrainAs(username){
                top.linkTo(image.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            })

        Text(text="Name",modifier = Modifier.constrainAs(name){
            top.linkTo(username.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        })

        Row(horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .constrainAs(row) {
                    top.linkTo(name.bottom)
                }) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "T1", fontWeight = FontWeight.Bold)
                Text(text = "Sub T1")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "T1", fontWeight = FontWeight.Bold)
                Text(text = "Sub T1")
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "T1", fontWeight = FontWeight.Bold)
                Text(text = "Sub T1")
            }
        }

        Button(onClick = { /*TODO*/ },modifier = Modifier.constrainAs(btn1){
            start.linkTo(parent.start)
            end.linkTo(btn2.start)
            top.linkTo(row.bottom)
        }) {
            Text(text = "Button1", fontSize = 16.sp)
        }
        Button(onClick = { /*TODO*/ },modifier = Modifier.constrainAs(btn2){
            start.linkTo(btn1.end)
            end.linkTo(parent.end)
            top.linkTo(row.bottom)
        }) {
            Text(text = "Button2", fontSize = 16.sp)
        }

    }
}

private fun portraitConstraints(margin: Dp):ConstraintSet{
    return ConstraintSet {
        val image = createRefFor("image")
        val name = createRefFor("name")
        val username = createRefFor("username")
        val row = createRefFor("row")
        val btn1 = createRefFor("btn1")
        val btn2 = createRefFor("btn2")
        val guideline = createGuidelineFromTop(0.1f)
        constrain(image){
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(name){
            top.linkTo(image.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(username){
            top.linkTo(name.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(row){
            top.linkTo(username.bottom)
        }
        constrain(btn1){
            top.linkTo(row.bottom, margin = margin)
            start.linkTo(parent.start)
            end.linkTo(btn2.start)
        }
        constrain(btn2){
            top.linkTo(row.bottom, margin = margin)
            start.linkTo(btn1.end)
            end.linkTo(parent.end)
        }
    }
}

private fun layoutConstraints(margin: Dp):ConstraintSet{
    return ConstraintSet {
        val image = createRefFor("image")
        val name = createRefFor("name")
        val username = createRefFor("username")
        val row = createRefFor("row")
        val btn1 = createRefFor("btn1")
        val btn2 = createRefFor("btn2")
        constrain(image){
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(name){
            top.linkTo(image.bottom)
            start.linkTo(image.start)
            end.linkTo(image.end)
        }
        constrain(username){
            top.linkTo(name.bottom)
            start.linkTo(name.start)
            end.linkTo(name.end)
        }
        constrain(row){
            top.linkTo(parent.top)
            start.linkTo(image.end)
            end.linkTo(parent.end)
        }
        constrain(btn1){
            top.linkTo(row.bottom, margin = margin)
            start.linkTo(image.end)
            end.linkTo(btn2.start)
        }
        constrain(btn2){
            top.linkTo(row.bottom, margin = margin)
            start.linkTo(btn1.end)
            end.linkTo(parent.end)
        }
    }
}

@Composable
fun ProfilePage(){
    Card(elevation = 6.dp,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp, vertical = 48.dp)
            .border(width = 2.dp, color = Color.Magenta, shape = RoundedCornerShape(30.dp))){
            
            BoxWithConstraints {
                val constraints = if(minWidth<600.dp) portraitConstraints(16.dp) else layoutConstraints(16.dp)

                ConstraintLayout(constraints, modifier = Modifier.verticalScroll(rememberScrollState())) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Red, shape = CircleShape)
                            .layoutId("image"),
                        contentScale = ContentScale.Crop
                    )

                    Text(text="Username", fontWeight = FontWeight.Bold,
                        modifier = Modifier.layoutId("username"))

                    Text(text="Name",modifier = Modifier.layoutId("name"))

                    Row(horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .layoutId("row")) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "T1", fontWeight = FontWeight.Bold)
                            Text(text = "Sub T1")
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "T1", fontWeight = FontWeight.Bold)
                            Text(text = "Sub T1")
                        }
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = "T1", fontWeight = FontWeight.Bold)
                            Text(text = "Sub T1")
                        }
                    }

                    Button(onClick = { /*TODO*/ },modifier = Modifier.layoutId("btn1")
                    ) {
                        Text(text = "Button1", fontSize = 16.sp)
                    }
                    Button(onClick = { /*TODO*/ },modifier = Modifier.layoutId("btn2")) {
                        Text(text = "Button2", fontSize = 16.sp)
                    }
                }
            }
            
        
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePagePreview(){
    ProfilePage()
}
