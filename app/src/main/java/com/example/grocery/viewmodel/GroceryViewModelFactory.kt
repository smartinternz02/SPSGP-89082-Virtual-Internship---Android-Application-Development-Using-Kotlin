package com.example.grocery.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.grocery.repository.GroceryRepository

class GroceryViewModelFactory(private val repository: GroceryRepository) :ViewModelProvider.NewInstanceFactory (){

    override fun <T: ViewModel> create(modelClass: Class<T>):T{
        return GroceryViewModel(repository) as T
    }
}