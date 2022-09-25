package com.example.grocery.repository

import com.example.grocery.database.GroceryDatabase
import com.example.grocery.database.GroceryItems

class GroceryRepository(private val database: GroceryDatabase) {

    suspend fun insert(items:GroceryItems)=database.getGroceryDao().insert(items)
    suspend fun delete(items: GroceryItems)=database.getGroceryDao().delete(items)

    fun getAllItems()=database.getGroceryDao().getAllItems()
}