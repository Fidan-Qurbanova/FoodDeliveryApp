package com.example.fooddeliveryapp.di

import com.example.fooddeliveryapp.data.datasource.FoodDatasource
import com.example.fooddeliveryapp.data.repo.FoodRepo
import com.example.fooddeliveryapp.data.retrofit.ApiUtils
import com.example.fooddeliveryapp.data.retrofit.FoodsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideFoodDatasource(foodsDao: FoodsDao):FoodDatasource{
        return FoodDatasource(foodsDao)
    }
    @Provides
    @Singleton
    fun provideFoodRepo(fds:FoodDatasource):FoodRepo{
        return FoodRepo(fds)
    }

    @Provides
    @Singleton
    fun provideFoodsDao():FoodsDao{
        return ApiUtils.getFoodsDao()
    }

}