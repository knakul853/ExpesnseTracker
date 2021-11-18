package com.knakul853.expensetracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.knakul853.expensetracker.data.Transaction

class MainActivity : AppCompatActivity() {
    private lateinit var transactions: ArrayList<Transaction>
    lateinit var transactionAdapter: TransactionAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        transactions = arrayListOf(
            Transaction("Apple", -400.04),
            Transaction("Game", 40.04),
            Transaction("Crypto", 45400.04),
            Transaction("Market", -600.0),
            Transaction("wine", 2001.03)
        )

        transactionAdapter = TransactionAdapter(transactions)
        findViewById<RecyclerView>(R.id.recyclerview).apply {
            adapter = transactionAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        updateDashboard()
    }

    private fun updateDashboard() {

        val totalBalance = transactions.map{it.amount}.sum()
        val totalBudget = transactions.filter { it.amount>0 }.map { it.amount }.sum()
        val totalExpense = totalBalance - totalBudget

        findViewById<TextView>(R.id.balance).text = "$%.02f".format(totalBalance)
        findViewById<TextView>(R.id.expense).text = "$%.02f".format(totalExpense)
        findViewById<TextView>(R.id.budget).text = "$%.02f".format(totalBudget)
    }


}