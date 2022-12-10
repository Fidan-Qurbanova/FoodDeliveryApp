package com.example.fooddeliveryapp.data.repo

import com.example.fooddeliveryapp.data.datasource.FoodDatasource
import com.example.fooddeliveryapp.data.entity.Foods
import com.example.fooddeliveryapp.data.entity.FoodsCart

class FoodRepo(var fDs:FoodDatasource) {


    suspend fun loadAllFoods():List<Foods> = fDs.loadAllFoods()

    suspend fun loadFoodsCart(userName :String):List<FoodsCart> = fDs.loadFoodsCart(userName)

    suspend fun deleteFood(cartId:Int,username: String) = fDs.deleteFood(cartId,username)
    suspend fun insertFood(name:String, image:String, price:Int, category:String, orderAmount:Int, username: String)
            = fDs.insertFood(name,image, price, category, orderAmount, username)
}