import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.ExperimentalResourceApi

/*********************************************************************
 * this loginForm to check if user enters correct login information
 ********************************************************************/
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

        /******************************************
        this text field is binded to username
         *****************************************/
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Username") },
            leadingIcon = { Icon(Icons.Filled.Person, contentDescription = null) }
        )
        Spacer(modifier = Modifier.height(20.dp))

        /*********************************
        this text field is binded to password
         *********************************/

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
                /********************************
                 * check is login information is correct
                 ******************************/

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
        /*
            this text is binded to errorMsg, to prompt wrong username or password
         */
        Text("$errorMsg", color = Color.Red)

    }
}

/*******************************************************************************
login validation function
for now, it is hardcoded for demo, username == "jun" && password == "1234"
 ******************************************************************************/
fun login(username : String, password: String) : Boolean{
    return username == "jun" && password == "1234"
}