package com.example.formularios

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formularios.ui.theme.FormulariosTheme

class InicioSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedprefs = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        var name = sharedprefs.getString("nombre", "")

        if (name == null) name = ""

        var nombre = intent.getStringExtra("nombre")
        if (nombre == null) nombre = "No hay nombre"
        var edad = intent.getStringExtra("edad")
        if (edad == null) edad = "No hay edad"
        var email = intent.getStringExtra("email")
        if (email == null) email = "No hay email"

        enableEdgeToEdge()
        setContent {
            Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Greeting(
                    name = name,
                    edad = edad,
                    email = email,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, edad: String, email: String, modifier: Modifier = Modifier) {
    Surface {
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(10.dp).fillMaxSize()
        ){
            Text(
                text = "Bienvenido $name!",
                modifier = modifier.padding(10.dp)
            )
            Text(
                text = "Edad: $edad",
                modifier = modifier.padding(10.dp)
            )
            Text(
                text = "Email: $email",
                modifier = modifier.padding(10.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    FormulariosTheme {
        Greeting("Android")
    }
}