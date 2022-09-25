package com.example.grocery

import com.example.grocery.database.GroceryItems



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GroceryAdapter(var list: List<GroceryItems>, private val groceryItemClickInterface: GroceryItemClickInterface): RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder>() {

    interface GroceryItemClickInterface{
        fun onItemClick(groceryItems: GroceryItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grocery_items, parent, false)
        return GroceryViewHolder(view)
    }

    override fun onBindViewHolder(holder: GroceryViewHolder, position: Int) {
        holder.name.text = list[position].itemName
        holder.quantity.text = list[position].itemQuantity.toString()
        holder.rate.text = "Rs. "+ list[position].itemPrice.toString()
        val itemTotal: Int = list[position].itemPrice * list[position].itemQuantity
        holder.amount.text = "Rs. "+ itemTotal.toString()
        holder.delete.setOnClickListener{
            groceryItemClickInterface.onItemClick(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class GroceryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.findViewById(R.id.textViewItemName)
        val quantity: TextView = itemView.findViewById(R.id.textViewQuantity)
        val rate: TextView = itemView.findViewById(R.id.textViewRate)
        val amount: TextView = itemView.findViewById(R.id.textViewTotalAmt)
        val delete: ImageView = itemView.findViewById(R.id.textViewDelete)
    }
}
