import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

/*****************************************************************
the composable function will be call after login
it shows welcome message and an image. if you like it, click the button
 ******************************************************************/
@OptIn(ExperimentalResourceApi::class)
@Composable
fun WelcomeForm() {
    /*
        the count variable records how many people like
     */
    var count by remember { mutableStateOf(0) }

    Column(Modifier.fillMaxSize().padding(bottom = 60.dp))
    {
        Card(
            elevation = 6.dp
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(bottom = 20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                /*****************************
                welcome message
                 *****************************/
                Text(
                    text = "WelcomeForm to my App",
                    style = MaterialTheme.typography.h4,
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))

                /*
                    show image
                    for now, it is hardcoded for demo
                 */
                Image(
                    painterResource("worker.png"),
                    contentDescription = "person image",
                    modifier = Modifier.fillMaxSize().weight(1f),
                    contentScale = ContentScale.FillBounds
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "people like: $count", textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.width(10.dp))

                    /*********************************
                    for people who like the image
                     *********************************/
                    IconButton(
                        /***************************************************
                        count will be added up, when clicking the button
                         ***************************************************/
                        onClick = {
                            count++;
                        },
                        modifier = Modifier.align(alignment = Alignment.CenterVertically)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            tint = Color.Green,
                            contentDescription = "people like"
                        )
                    }
                }
            }
        }
    }
}
