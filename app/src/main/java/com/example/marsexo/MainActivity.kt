// Ailyn Diaz
// CSC Android Kotlin ---> RetroMars Assignment
// 4/16/24

package com.example.marsexo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.marsexo.ui.MarsApp
import com.example.marsexo.ui.theme.MarsPhotosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MarsPhotosTheme {
                Surface(
                    color = Color.DarkGray, // Set the background color to dark gray
                    modifier = Modifier.fillMaxSize(),
                ) {
                    MarsApp()
                }
            }
        }
    }
}
