package com.example.fooddeliveryapp.data.datasource

import com.example.fooddeliveryapp.data.entity.Foods
import com.example.fooddeliveryapp.data.entity.FoodsCart
import com.example.fooddeliveryapp.data.retrofit.FoodsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field

class FoodDatasource(var fDao:FoodsDao) {

    suspend fun loadAllFoods():List<Foods> =
        withContext(Dispatchers.IO) {
            fDao.loadAllFoods().foods
        }

    suspend fun loadFoodsCart(userName :String):List<FoodsCart> =
        withContext(Dispatchers.IO) {
                fDao.loadFoodsCart(userName).foods_cart

        }

    suspend fun deleteFood(cartId:Int,username: String) = fDao.deleteFood(cartId,username)
    suspend fun insertFood(name:String, image:String, price:Int, category:String, orderAmount:Int, username: String)
                           = fDao.insertFood(name,image, price, category, orderAmount, username)


}