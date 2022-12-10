package com.example.fooddeliveryapp.ui.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.fooddeliveryapp.data.entity.FoodsCart
import com.example.fooddeliveryapp.data.repo.FoodRepo
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewmodel @Inject constructor(var fRepo: FoodRepo) :ViewModel() {
    var foodCartList = MutableLiveData<List<FoodsCart>>()
    val currentUser = FirebaseAuth.getInstance().currentUser?.email.toString()

init {
    loadFoodsCart(currentUser)
}

     fun loadFoodsCart(userName :String){
        CoroutineScope(Dispatchers.Main).launch {
            try {
                foodCartList.value = fRepo.loadFoodsCart(userName)
            }catch (e:java.lang.Exception){
                foodCartList.value = ArrayList<FoodsCart>()
            }
        }
    }

    fun  deleteFood(cartId:Int,username: String) {
        CoroutineScope(Dispatchers.Main).launch {
            fRepo.deleteFood(cartId, username)
            loadFoodsCart(username)
        }
    }


}