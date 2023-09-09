package com.shubhans.readercapstoneappliaction

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.firestore.FirebaseFirestore
import com.shubhans.readercapstoneappliaction.ui.theme.ReaderCapstoneAppliactionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReaderCapstoneAppliactionTheme {
                val db =FirebaseFirestore.getInstance()
                val user:MutableMap<String,Any> =HashMap()
                user["firstName"] ="shubhans"
                user["lastName"] ="singh"


                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    db.collection("users")
                        .add(user)
                        .addOnSuccessListener {
                            Log.d("Gh","oncreate:${it.id}")
                        }
                        .addOnFailureListener{
                            Log.d("Gh","oncreate:${it}")
                        }
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ReaderCapstoneAppliactionTheme {
        Greeting("Android")
    }
}