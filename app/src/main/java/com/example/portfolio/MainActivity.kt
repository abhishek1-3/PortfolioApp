package com.example.portfolio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.portfolio.screens.ContactScreen
import com.example.portfolio.screens.HomeScreen
import com.example.portfolio.screens.ProjectScreen
import com.example.portfolio.screens.SkillScreen
import com.example.portfolio.ui.theme.PortfolioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PortfolioTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen(){

    var currentscreen by remember { mutableStateOf("Home") }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier.weight(1f)
        ){
            when(currentscreen){
                "Home" -> HomeScreen()
                "Projects" -> ProjectScreen()
                "Skills" -> SkillScreen()
                "Contact" -> ContactScreen()
            }
        }
        NavigationBar {
            NavigationBarItem(
                selected = if(currentscreen=="Home") true else false,
                onClick = { currentscreen = "Home" },
                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = "Home") },
                label = {Text("Home")}
            )
            NavigationBarItem(
                selected = if(currentscreen=="Projects") true else false,
                onClick = { currentscreen = "Projects" },
                icon = { Icon(imageVector = Icons.Default.List, contentDescription = "Projects") },
                label = {Text("Projects")}

            )
            NavigationBarItem(
                selected = if(currentscreen=="Skills") true else false,
                onClick = { currentscreen = "Skills" },
                icon = { Icon(imageVector = Icons.Default.Build, contentDescription = "Skills") },
                label = {Text("Skills")}
            )
            NavigationBarItem(
                selected = if(currentscreen=="Contact") true else false,
                onClick = { currentscreen = "Contact" },
                icon = { Icon(imageVector = Icons.Default.Person, contentDescription = "Contact") },
                label = {Text("Contact")}
            )

        }
    }
}