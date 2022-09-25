package com.example.grocery.database

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface GroceryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert (item: GroceryItems)

    @Delete
    suspend fun delete(item: GroceryItems)

    @Query("SELECT * From grocery_items")
    fun getAllItems(): LiveData<List<GroceryItems>>
}