package com.example.formularios

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
    var nombre by remember { mutableStateOf(("")) }
    var email by remember { mutableStateOf(("")) }
    var contrasenia by remember { mutableStateOf(("")) }

    var resultado by remember { mutableStateOf("") }

    var contexto = LocalContext.current
    var intent = Intent(contexto, InicioSesion::class.java)
    var intent2 = Intent(contexto, MainActivity::class.java)
    intent.putExtra("nombre", nombre)
    intent.putExtra("contrasenia", contrasenia)

    val sharedprefs = LocalContext.current
        .getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)

    val existeNombre = sharedprefs.contains("${nombre}_email") // Comprueba si ya hay un registro de email asociado al nombre en SharedPreferences
    val existeEmail = sharedprefs.all.values.contains(email) // Revisa si el email ya está guardado

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
                value = contrasenia,
                onValueChange = { x ->
                    contrasenia = x
                },
                label = { Text(text = "Contraseña:") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation('*') // Para que la contraseña no sea visible
            )

            Spacer(Modifier.height(16.dp))

            Button(onClick = {
                if (nombre.length < 3) {
                    resultado = "El nombre debe tener más de 3 caracteres"
                } else if (existeNombre) {
                    resultado = "Ese nombre ya existe"
                } else if (!email.contains("@")) {
                    resultado = "El email no es válido"
                } else if (existeEmail) {
                    resultado = "El email no es válido"
                } else if (!Regex("^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#\\\$%^&*(),.?\":{}|<>]).{8,}\$").matches(
                        contrasenia)
                ) {
                    resultado = "La contraseña no es válida"
                } else {
                    with(sharedprefs.edit()){ // Guarda el email y la contraseña en SharedPreferences y navega a la siguiente pantalla
                        putString("${nombre}_email",email)
                        putString("${nombre}_contraseña",contrasenia)
                        apply()
                    }
                    resultado = "Credenciales guardadas"
                    contexto.startActivity(intent)
                }
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9162EE),
                    contentColor = Color.White
                )) {
                Text(text = "Enviar")
            }

            Button(onClick = {
                contexto.startActivity(intent2)
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE1BEE7),
                    contentColor = Color.White
                )) {
                Text(text = "Volver a inicio")
            }

            Spacer(Modifier.height(16.dp))

            Text(text = resultado)
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