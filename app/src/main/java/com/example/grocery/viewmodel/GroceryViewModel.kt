package com.example.grocery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.grocery.database.GroceryItems
import com.example.grocery.repository.GroceryRepository
import kotlinx.coroutines.launch

class GroceryViewModel(private val repository: GroceryRepository): ViewModel() {
     fun insert(items: GroceryItems)=viewModelScope.launch {
        repository.insert(items)
    }


    fun delete(items: GroceryItems)=viewModelScope.launch{
        repository.delete(items)}

    fun getAllItems()=repository.getAllItems()
}