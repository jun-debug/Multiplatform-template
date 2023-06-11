@file:OptIn(ExperimentalMaterialApi::class)
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import ui.theme.AppTheme
import ui.theme.md_theme_light_background
import ui.theme.md_theme_light_onSecondary


@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {
    AppTheme {
        var hello by remember { mutableStateOf("Assignment") }
        var isLogin by remember { mutableStateOf(false) }
        Scaffold(
            topBar = {
                TopAppBar (modifier = Modifier.fillMaxWidth(), backgroundColor = Color.Blue, contentColor = Color.White) {
                    Text(text = "$hello", modifier = Modifier.fillMaxWidth().weight(1f), textAlign = TextAlign.Center)
                    IconButton(
                        onClick = {
                            if (hello == "Assignment")
                                hello = "Hello world!"
                            else hello = "Assignment"
                        },
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)){
                        Icon(imageVector = Icons.Default.Info, contentDescription = "information")
                    }
                }
            },
            bottomBar = { BottomAppBar { Text("Copyright (c) 2023 Jun Liang", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center) } }
        )
        {
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(!isLogin) {
                    LoginForm(onLoginClicked = {
                        isLogin = true

                    })
                }
                AnimatedVisibility(isLogin) {
                    Welcome()
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Welcome(){
    var count by remember { mutableStateOf(0) }
    Column (Modifier.fillMaxSize().padding(bottom = 60.dp))
    {
        Card(
            elevation = 6.dp
        ) {
            Column(modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
               horizontalAlignment = Alignment.CenterHorizontally,
               verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Welcome to my App",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Image(
                    painterResource("worker.png"),
                    contentDescription = "person image",
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentScale = ContentScale.FillBounds
                )
                Row (verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                    Text(text = "people like: $count", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(
                        onClick = {
                            count ++;
                        },
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)){
                        Icon(imageVector = Icons.Default.Check, tint = Color.Green, contentDescription = "people like")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoginForm(onLoginClicked: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", color = Color.Blue, fontSize = 48.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(80.dp))
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) }
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Password") },
            leadingIcon = { Icon(Icons.Filled.Lock, contentDescription = null) },
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = {
                if (login(username, password)) {
                    errorMsg = ""
                    onLoginClicked()
                }
                else {
                    errorMsg = "Wrong username or passowd!"
                }
            }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Filled.Lock, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Login", color = Color.White)
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text("$errorMsg", color = Color.Red)

    }
}

fun login(username : String, password: String) : Boolean{
    return username == "jun" && password == "1234"
}

expect fun getPlatformName(): String