package com.example.financetracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.financetracker.ui.screens.DashboardScreen
import com.example.financetracker.ui.screens.AddTransactionScreen
import com.example.financetracker.ui.screens.TransactionListScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "dashboard"
    ) {
        composable("dashboard") {
            DashboardScreen(
                navController = navController,
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme
            )
        }
        composable("add_transaction") { AddTransactionScreen(navController) }
        composable("transaction_list") { TransactionListScreen(navController) }
    }
}
