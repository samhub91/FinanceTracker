package com.example.financetracker.model

data class Transaction(
    val id: Int,
    val description: String,
    val amount: Double,
    val type: TransactionType // Enum to distinguish Income/Expense
)

enum class TransactionType {
    INCOME,
    EXPENSE
}
