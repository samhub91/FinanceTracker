package com.example.financetracker.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

import androidx.compose.ui.text.input.KeyboardType
import com.example.financetracker.model.Transaction
import com.example.financetracker.model.TransactionRepository
import com.example.financetracker.model.TransactionType
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(navController: NavController) {
    var amount by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isIncome by remember { mutableStateOf(true) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                "Add Transaction",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = amount,
                onValueChange = { amount = it },
                label = { Text("Amount") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Type:", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(selected = isIncome, onClick = { isIncome = true })
                Text("Income")
                Spacer(modifier = Modifier.width(8.dp))
                RadioButton(selected = !isIncome, onClick = { isIncome = false })
                Text("Expense")
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    val amt = amount.toDoubleOrNull() ?: 0.0
                    if (description.isNotBlank() && amt > 0) {
                        TransactionRepository.addTransaction(
                            Transaction(
                                id = TransactionRepository.getTransactions().size + 1,
                                description = description,
                                amount = amt,
                                type = if (isIncome) TransactionType.INCOME else TransactionType.EXPENSE
                            )
                        )
                        navController.popBackStack()
                    } else {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar("Please enter valid description and amount.")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "Save",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("Save Transaction")
            }
        }
    }
}



