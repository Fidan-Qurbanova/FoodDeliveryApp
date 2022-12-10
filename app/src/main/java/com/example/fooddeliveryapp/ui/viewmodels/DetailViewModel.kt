package com.example.fooddeliveryapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.data.repo.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(var fRepo :FoodRepo) :ViewModel() {

    fun insertFood(name:String,
                   image:String,
                   price:Int,
                   category:String,
                   orderAmount:Int,
                   username:String){
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.insertFood(name,image,price, category, orderAmount, username)
        }
    }

}