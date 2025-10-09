package com.example.formularios

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
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
import com.example.formularios.ui.theme.FormulariosTheme

class InicioSesion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FormulariosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier) {
    var nombre by remember { mutableStateOf(("")) }
    var contrasenia by remember { mutableStateOf(("")) }

    var resultado by remember { mutableStateOf("") }

    var contexto = LocalContext.current
    var intent = Intent(contexto, DatosUsuario::class.java)
    var intent2 = Intent(contexto, MainActivity::class.java)

    val sharedprefs = LocalContext.current
        .getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)

    Surface {
        Column (verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.padding(10.dp).fillMaxSize()
        ){
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
                value = contrasenia,
                onValueChange = { x ->
                    contrasenia = x
                },
                label = { Text(text = "Contraseña:") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = PasswordVisualTransformation('*')
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val contraseniaGuardada = sharedprefs.getString("${nombre}_contraseña", null)

                if (contraseniaGuardada == null) {
                    resultado = "No existe el usuario"
                } else if (contraseniaGuardada != contrasenia) {
                    resultado = "Contraseña incorrecta"
                } else {
                    resultado = "Inicio de sesión correcto"
                    intent.putExtra("nombre", nombre)
                    contexto.startActivity(intent)
                }
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9162EE),
                    contentColor = Color.White
                )) {
                Text("Iniciar Sesión")
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

            Text(resultado)
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