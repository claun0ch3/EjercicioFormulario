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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.formularios.ui.theme.FormulariosTheme

class DatosUsuario : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            FormulariosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaDatos(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaDatos(modifier: Modifier = Modifier) {
    val contexto = LocalContext.current
    val sharedPrefs = contexto.getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
    val editor = sharedPrefs.edit()

    var mensaje by remember { mutableStateOf("") }

    val nombreGuardado = (contexto as DatosUsuario).intent.getStringExtra("nombre") ?: ""

    var nombre by remember { mutableStateOf((nombreGuardado)) }
    var email by remember { mutableStateOf(sharedPrefs.getString("${nombreGuardado}_email", "") ?: "") }
    var contrasenia by remember { mutableStateOf(sharedPrefs.getString("${nombreGuardado}_contrasenia", "") ?: "") }


    Surface {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
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
                visualTransformation = PasswordVisualTransformation('*')
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                editor.putString(nombre, nombre)
                editor.putString("${nombre}_email", email)
                editor.putString("${nombre}_contrasenia", contrasenia)
                editor.apply()

                mensaje = "Datos guardados correctamente"

                val intent = Intent(contexto, MainActivity::class.java)
                contexto.startActivity(intent)
            },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF9162EE),
                    contentColor = Color.White
                )) {
                Text("Guardar cambios y volver")
            }
            Text(mensaje)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    FormulariosTheme {
        PantallaDatos()
    }
}