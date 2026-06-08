package com.example.sistemainventario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.example.sistemainventario.ui.theme.SistemaInventarioTheme
import com.example.sistemainventario.ui.login.LoginScreen
import com.example.sistemainventario.ui.dashboard.DashboardScreen
import com.example.sistemainventario.model.User

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SistemaInventarioTheme {
                var currentUser by rememberSaveable { mutableStateOf<User?>(null) }

                if (currentUser == null) {
                    LoginScreen(onLoginSuccess = { user -> currentUser = user })
                } else {
                    DashboardScreen(
                        user = currentUser!!,
                        onLogout = { currentUser = null }
                    )
                }
            }
        }
    }
}