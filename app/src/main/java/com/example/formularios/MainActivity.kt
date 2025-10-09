package com.example.formularios

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.formularios.ui.theme.FormulariosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormulariosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val image = painterResource(R.drawable.donut)
    var contexto = LocalContext.current



    Surface (color = Color.White) {
        Column (
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(5.dp)
                .fillMaxSize()
        ){
            Image(
                painter = image,
                contentDescription = "donutcito",
                contentScale = ContentScale.Crop,
                alpha = 0.5F,
                modifier = Modifier.size(300.dp)
            )

            Spacer(Modifier.height(16.dp))

            Text(text = "Donutcitos",
                style = TextStyle(fontSize = 24.sp)
            )

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                var intent = Intent(contexto, InicioSesion::class.java)
                contexto.startActivity(intent)
            }) {
                Text(text = "Iniciar Sesión")
            }

            Spacer(Modifier.height(16.dp))

            Text(text = "Si no tienes cuenta, debes registrate")
            Button(onClick = {
                var intent2 = Intent(contexto, RegistroSesion::class.java)
                contexto.startActivity(intent2)
            }) {
                Text(text = "Registrarse")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FormulariosTheme {
        Greeting("Android")
    }
}