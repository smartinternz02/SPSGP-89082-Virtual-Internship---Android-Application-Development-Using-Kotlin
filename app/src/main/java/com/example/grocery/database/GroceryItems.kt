package com.example.grocery.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName="grocery_items")
data class GroceryItems(

    @ColumnInfo(name="item_name")
    val itemName: String,
    @ColumnInfo(name="item_quantity")
    val itemQuantity: Int,
    @ColumnInfo(name="item_price")
    val itemPrice: Int
    ){

    @PrimaryKey(autoGenerate = true)
    var id: Int?=null
}