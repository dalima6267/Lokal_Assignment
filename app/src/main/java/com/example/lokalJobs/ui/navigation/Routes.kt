package com.example.lokalJobs.ui.navigation

sealed class Routes(val route: String) {
    object BottomBar : Routes("bottomBar")
}