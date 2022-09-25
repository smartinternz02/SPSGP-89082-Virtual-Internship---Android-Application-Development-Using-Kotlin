package com.example.grocery

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.grocery.database.GroceryDatabase
import com.example.grocery.database.GroceryItems
import com.example.grocery.repository.GroceryRepository
import com.example.grocery.viewmodel.GroceryViewModel
import com.example.grocery.viewmodel.GroceryViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(), GroceryAdapter.GroceryItemClickInterface {

    lateinit var recyclerView: RecyclerView
    lateinit var addFloatingActionButton: FloatingActionButton
    lateinit var list: List<GroceryItems>
    lateinit var groceryAdapter: GroceryAdapter
    lateinit var groceryViewModel:GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView=findViewById(R.id.recyclerView)
        addFloatingActionButton=findViewById(R.id.floatingButton)
        list=ArrayList<GroceryItems>()
        groceryAdapter= GroceryAdapter(list, this)
        recyclerView.layoutManager=LinearLayoutManager(this)
        recyclerView.adapter=groceryAdapter
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)
        groceryViewModel = ViewModelProvider(this, factory)[GroceryViewModel::class.java]
        groceryViewModel.getAllItems().observe(this, Observer {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()
        })

        addFloatingActionButton.setOnClickListener {
            openDialog()
        }

    }

    private fun openDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.grocery_dialog)
        val cancelBtn = dialog.findViewById<Button>(R.id.BtnCancel)
        val addBtn = dialog.findViewById<Button>(R.id.BtnAdd)
        val itemEdt = dialog.findViewById<EditText>(R.id.dialogItemName)
        val itemPriceEdt = dialog.findViewById<EditText>(R.id.dialogItemPrice)
        val itemQuantityEdt = dialog.findViewById<EditText>(R.id.dialogItemQuantity)
        cancelBtn.setOnClickListener {
            dialog.dismiss()
        }
        addBtn.setOnClickListener {
            val itemName: String = itemEdt.text.toString()
            val itemPrice: String = itemPriceEdt.text.toString()
            val itemQuantity: String = itemQuantityEdt.text.toString()

            if(itemName == "" || itemPrice =="" || itemQuantity=="" ){
                Toast.makeText(applicationContext, "Please Enter all the data..", Toast.LENGTH_SHORT).show()

            }
            else{
                val qty: Int = itemQuantity.toInt()
                val pr: Int = itemPrice.toInt()
                val items = GroceryItems(itemName, qty, pr)
                groceryViewModel.insert(items)
                Toast.makeText(applicationContext, "Item Inserted..", Toast.LENGTH_SHORT).show()
                groceryAdapter.notifyDataSetChanged()
                dialog.dismiss()
            }

        }
        dialog.show()
    }

    override fun onItemClick(groceryItems: GroceryItems) {
        groceryViewModel.delete(groceryItems)
        groceryAdapter.notifyDataSetChanged()
        Toast.makeText(applicationContext, "Item Deleted..", Toast.LENGTH_SHORT).show()
    }
}