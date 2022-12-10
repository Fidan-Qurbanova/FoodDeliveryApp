package com.example.fooddeliveryapp.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.entity.FoodsCart
import com.example.fooddeliveryapp.databinding.AddCartDesignBinding
import com.example.fooddeliveryapp.ui.viewmodels.CartViewmodel

class FoodCartAdapter(var mContext:Context, var foodsCartList:List<FoodsCart>, var viewModel:CartViewmodel)
    :RecyclerView.Adapter<FoodCartAdapter.CartDesignHolder>()
{
    inner class CartDesignHolder(var binding: AddCartDesignBinding):RecyclerView.ViewHolder(binding.root)
    var resultPrice=0
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartDesignHolder {
        var binding : AddCartDesignBinding
            = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.add_cart_design,parent,false)
        return CartDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CartDesignHolder, position: Int) {
        val foodsCart = foodsCartList.get(position)
        val b = holder.binding
        b.foodsCart= foodsCart
        resultPrice+=foodsCart.price
        showImage(foodsCart.image,b.imgCart)

        b.imageViewDelete.setOnClickListener {
//            Log.e("Result delete",foodsCart.userName)
            viewModel.deleteFood(foodsCart.cartId,foodsCart.userName)
        }
        b.textViewcartPrdPrice.text = resultPrice.toString()
    }

    override fun getItemCount(): Int {
        return foodsCartList.size
    }


    fun showImage(imageName:String,imgView: ImageView){
        val url = "http://kasimadalan.pe.hu/foods/images/$imageName"
        Glide.with(mContext).load(url).override(600,600).into(imgView)
    }

}