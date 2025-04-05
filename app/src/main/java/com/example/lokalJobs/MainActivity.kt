package com.example.lokalJobs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.lokalJobs.ui.navigation.MainNavGraph
import com.example.lokalJobs.ui.theme.Theme
import dagger.hilt.android.AndroidEntryPoint


const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   // @Inject lateinit var jobApi: JobApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Theme {
                MainNavGraph(navController = rememberNavController())
            }
        }
    }
}

