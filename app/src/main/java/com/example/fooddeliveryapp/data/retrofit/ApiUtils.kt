package com.example.fooddeliveryapp.data.retrofit

import retrofit2.Retrofit

class ApiUtils {
    companion object{
        var BASE_URL ="http://kasimadalan.pe.hu/"

        fun getFoodsDao(): FoodsDao {
            return RetrofitClient.getClient(BASE_URL).create(FoodsDao::class.java)
        }
    }
}