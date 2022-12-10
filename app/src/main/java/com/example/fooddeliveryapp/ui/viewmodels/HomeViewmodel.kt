package com.example.fooddeliveryapp.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.data.entity.Foods
import com.example.fooddeliveryapp.data.entity.FoodsCart
import com.example.fooddeliveryapp.data.repo.FoodRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(var fRepo :FoodRepo)  :ViewModel() {
    var foodList = MutableLiveData<List<Foods>>()

    init {
        loadAllFoods()
    }

    fun loadAllFoods() {
        CoroutineScope(Dispatchers.Main).launch {
            foodList.value = fRepo.loadAllFoods()
        }
    }
}