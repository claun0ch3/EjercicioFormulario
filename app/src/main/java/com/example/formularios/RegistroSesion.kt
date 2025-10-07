package com.example.formularios

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.formularios.ui.theme.FormulariosTheme

class RegistroSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormulariosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting3(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting3(name: String, modifier: Modifier = Modifier) {

    var nombre by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var edad by remember { mutableStateOf(TextFieldValue("")) }
    var contrasenia by remember { mutableStateOf(TextFieldValue("")) }

    var resultado by remember { mutableStateOf("") }

    var contexto = LocalContext.current
    var intent = Intent(contexto, InicioSesion::class.java)
    intent.putExtra("nombre", nombre.text)
    intent.putExtra("edad", edad.text)
    intent.putExtra("email", email.text)

    val sharedprefs = LocalContext.current
        .getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
    val editor = sharedprefs.edit()

    Surface (color = Color.White) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(10.dp).fillMaxSize()
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { x ->
                    nombre = x
                },
                label = { Text(text = "Nombre:") },
                placeholder = { Text(text = "Clau") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = email,
                onValueChange = { x ->
                    email = x
                },
                label = { Text(text = "Email:") },
                placeholder = { Text(text = "claudia@gmail.com") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            OutlinedTextField(
                value = edad,
                onValueChange = { x ->
                    edad = x
                },
                label = { Text(text = "Edad:") },
                placeholder = { Text(text = "20") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = contrasenia,
                onValueChange = { x ->
                    contrasenia = x
                },
                label = { Text(text = "Contraseña:") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation('*')
            )
            Button(onClick = {
                if (nombre.text.length < 3) {
                    resultado = "El nombre debe tener más de 3 caracteres"
                } else if (!email.text.contains("@")) {
                    resultado = "El email no es válido"
                } else if (!edad.text.isDigitsOnly()) {
                    resultado = "La edad no es válida"
                } else if (!Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\\$%^&*(),.?\":{}|<>]).{8,}\$").matches(
                        contrasenia.text
                    )
                ) {
                    resultado = "La contraseña no es válida"
                } else {
                    resultado = "Registro exitoso"
                    editor.putString("nombre", nombre.text)
                    editor.commit()
                    contexto.startActivity(intent)
                }
            }) {
                Text(text = "Registrar")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    FormulariosTheme {
        Greeting3("Android")
    }
}