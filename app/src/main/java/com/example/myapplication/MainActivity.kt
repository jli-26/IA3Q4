package com.example.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                ResponsiveScreen()
            }
        }
    }
}
@SuppressLint("UnusedBoxWithConstraintsScope")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResponsiveScreen() {

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {

        val isWideScreen = maxWidth > 600.dp

        if (isWideScreen) {
            WideLayout()
        } else {
            NarrowLayout()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NarrowLayout() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Responsive App") }
            )
        }
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {

            Text(
                text = "Phone Layout",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {}) {
                Text("Primary Action")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Card Title", style = MaterialTheme.typography.titleMedium)
                    Text("This is detail content displayed in a column layout.")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                label = { Text("Enter text") }
            )
        }
    }
}

@Composable
fun WideLayout() {

    var selectedItem by remember { mutableStateOf("Home") }

    Row(modifier = Modifier.fillMaxSize()) {

        NavigationRail(
            modifier = Modifier
                .fillMaxHeight()
        ) {

            NavigationRailItem(
                selected = selectedItem == "Home",
                onClick = { selectedItem = "Home" },
                icon = { Icon(Icons.Default.Home, contentDescription = null) },
                label = { Text("Home") }
            )

            NavigationRailItem(
                selected = selectedItem == "Profile",
                onClick = { selectedItem = "Profile" },
                icon = { Icon(Icons.Default.Person, contentDescription = null) },
                label = { Text("Profile") }
            )

            NavigationRailItem(
                selected = selectedItem == "Settings",
                onClick = { selectedItem = "Settings" },
                icon = { Icon(Icons.Default.Settings, contentDescription = null) },
                label = { Text("Settings") }
            )
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp)
        ) {

            Column {

                Text(
                    text = "Selected: $selectedItem",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn {

                    items((1..20).toList()) { item ->
                        ListItem(
                            headlineContent = { Text("Item $item") },
                            supportingContent = { Text("Details about item $item") }
                        )
                        Divider()
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 400)
@Composable
fun PhonePreview() {
    MyApplicationTheme {
        ResponsiveScreen()
    }
}

@Preview(showBackground = true, widthDp = 900)
@Composable
fun TabletPreview() {
    MyApplicationTheme {
        ResponsiveScreen()
    }
}