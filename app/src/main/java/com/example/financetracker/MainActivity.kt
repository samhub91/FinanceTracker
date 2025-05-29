package com.example.financetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.financetracker.ui.navigation.AppNavHost
import com.example.financetracker.ui.theme.FinanceTrackerTheme
import com.example.financetracker.ThemeViewModel // ✅ Make sure this import is here

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // ✅ Get the ThemeViewModel instance
            val themeViewModel: ThemeViewModel = viewModel()
            val isDark = themeViewModel.isDarkTheme.value

            // ✅ Apply your theme with dark mode
            FinanceTrackerTheme(darkTheme = isDark) {
                val navController = rememberNavController()

                // ✅ Pass required theme values to AppNavHost
                AppNavHost(
                    navController = navController,
                    isDarkTheme = isDark,
                    onToggleTheme = { themeViewModel.toggleTheme() }
                )
            }
        }
    }
}
