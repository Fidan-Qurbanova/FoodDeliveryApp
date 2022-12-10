package com.example.fooddeliveryapp.data.retrofit

import com.example.fooddeliveryapp.data.entity.CRUDResponse
import com.example.fooddeliveryapp.data.entity.FoodsCartResponse
import com.example.fooddeliveryapp.data.entity.FoodsResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDao {

    @GET("foods/getAllFoods.php")
    suspend fun loadAllFoods():FoodsResponse

    @POST("foods/getFoodsCart.php")
    @FormUrlEncoded
    suspend fun loadFoodsCart(@Field("userName") username :String ) : FoodsCartResponse

    @POST("foods/deleteFood.php")
    @FormUrlEncoded
    suspend fun deleteFood(@Field("cartId") cartId:Int,
                            @Field("userName") username: String) : CRUDResponse

    @POST("foods/insertFood.php")
    @FormUrlEncoded
    suspend fun insertFood(@Field("name") name:String,
                           @Field("image") image:String,
                           @Field("price") price:Int,
                           @Field("category") category:String,
                           @Field("orderAmount") orderAmount:Int,
                           @Field("userName") username: String
                          ):CRUDResponse

}