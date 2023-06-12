import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MainScreen() {

    var hello by remember { mutableStateOf("Assignment") }
    var isLogin by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = Color.Blue,
                contentColor = Color.White
            ) {
                /********************************************
                binding the text with var hello
                 *******************************************/
                Text(
                    text = "$hello",
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    textAlign = TextAlign.Center
                )

                /********************************************
                when click this button, it will change the title back and forth
                 *******************************************/

                IconButton(
                    onClick = {
                        if (hello == "Assignment")
                            hello = "Hello world!"
                        else hello = "Assignment"
                    },
                    modifier = Modifier.align(alignment = Alignment.CenterVertically)
                ) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "information")
                }
            }
        },
        bottomBar = {
            BottomAppBar {
                Text(
                    "Copyright (c) 2023 Jun Liang",
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    textAlign = TextAlign.Center
                )
                if (isLogin) {
                    IconButton(
                        onClick = {
                            isLogin = false
                        },
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ExitToApp,
                            contentDescription = "logout"
                        )
                    }
                }
            }
        }
    )
    {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /**************************************************************
            when it is not login, it is going to show the login form
             ************************************************************/

            AnimatedVisibility(!isLogin) {
                LoginForm(onLoginClicked = {
                    isLogin = true
                })
            }

            /*******************************************************
            it will show welcome form after login successfully
             *****************************************************/


            AnimatedVisibility(isLogin) {
                WelcomeForm()
            }
        }
    }

}