package com.example.financetracker.model

object TransactionRepository {
    private val transactions = mutableListOf<Transaction>()

    fun addTransaction(transaction: Transaction) {
        transactions.add(transaction)
    }

    fun getTransactions(): List<Transaction> = transactions.toList()
}
