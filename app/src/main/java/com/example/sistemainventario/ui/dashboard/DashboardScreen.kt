package com.example.sistemainventario.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sistemainventario.model.User
import com.example.sistemainventario.ui.inventory.InventoryScreen
import com.example.sistemainventario.ui.theme.MedicalBlue
import com.example.sistemainventario.ui.theme.DrawerItemBackground
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(user: User, onLogout: () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var currentScreen by remember { mutableStateOf("INVENTARIO") }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerContainerColor = MedicalBlue,
                modifier = Modifier.width(300.dp)
            ) {
                DrawerHeader(user)
                Spacer(modifier = Modifier.height(24.dp))
                DrawerBody(
                    onItemClick = { screen ->
                        currentScreen = screen
                        scope.launch { drawerState.close() }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        navigationIconContentColor = Color.Black,
                        titleContentColor = Color.Black,
                        actionIconContentColor = Color.Black
                    ),
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    },
                    actions = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(end = 8.dp)
                        ) {
                            Column(horizontalAlignment = Alignment.End) {
                                Text(
                                    text = user.nombre,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = user.email,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    textDecoration = androidx.compose.ui.text.style.TextDecoration.Underline
                                )
                            }
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile",
                                modifier = Modifier.size(45.dp),
                                tint = Color.Black
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when (currentScreen) {
                    "INVENTARIO" -> InventoryScreen()
                    else -> PlaceholderScreen(currentScreen)
                }
            }
        }
    }
}

@Composable
fun DrawerHeader(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = null,
            modifier = Modifier.size(80.dp),
            tint = Color.Black
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.nombre,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Text(
            text = user.email,
            fontSize = 14.sp,
            color = Color.Black.copy(alpha = 0.7f)
        )
        Text(
            text = user.telefono,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    }
}

@Composable
fun DrawerBody(onItemClick: (String) -> Unit) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        DrawerItem("INVENTARIO", "agregar, modificar, cargar, editar y mucho más.", Icons.Default.Inventory, onItemClick)
        DrawerItem("REPORTE", "robos, malogrado de bienes, perdidas de bienes", Icons.Default.Assessment, onItemClick)
        DrawerItem("TRANSFERENCIA DE RESPONSABLE", "", Icons.Default.SwapHoriz, onItemClick)
        DrawerItem("MOVIMIENTOS DE BIENES", "", Icons.Default.History, onItemClick)
        DrawerItem("CENTRO DE NOTIFICACIONES", "", Icons.Default.Notifications, onItemClick)
    }
}

@Composable
fun DrawerItem(title: String, subtitle: String, icon: ImageVector, onClick: (String) -> Unit) {
    Surface(
        onClick = { onClick(title) },
        shape = RoundedCornerShape(4.dp),
        color = DrawerItemBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontSize = 18.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.Black
            )
            if (subtitle.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = subtitle,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black.copy(alpha = 0.8f),
                    lineHeight = 16.sp
                )
            }
        }
    }
}

@Composable
fun PlaceholderScreen(name: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Pantalla de $name en desarrollo", style = MaterialTheme.typography.headlineSmall)
    }
}
