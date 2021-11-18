package com.knakul853.expensetracker

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.knakul853.expensetracker.data.Transaction

class TransactionAdapter(private val transactions: ArrayList<Transaction>):RecyclerView.Adapter<TransactionAdapter.TransactionViewHolder>() {

    inner class TransactionViewHolder(view: View):RecyclerView.ViewHolder(view) {

        var label: TextView = view.findViewById(R.id.label)
        var amount = view.findViewById<TextView>(R.id.amount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_layout, parent, false)

        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {

        val transaction = transactions[position]
        val context = holder.amount.context

        if(transaction.amount>=0){
            holder.apply {
                amount.text = " + $%.2f".format(transaction.amount)
                amount.setTextColor(ContextCompat.getColor(context, R.color.green))
            }
        }
        else{
            holder.apply {
                amount.text = " - $%.2f".format(Math.abs(transaction.amount))
                amount.setTextColor(ContextCompat.getColor(context, R.color.red))
            }
        }

        holder.label.text = transaction.label
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

}