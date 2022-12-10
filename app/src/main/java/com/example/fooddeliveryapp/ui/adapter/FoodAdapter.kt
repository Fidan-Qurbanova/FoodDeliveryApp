package com.example.fooddeliveryapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.data.entity.Foods
import com.example.fooddeliveryapp.databinding.CardDesignBinding
import com.example.fooddeliveryapp.ui.fragments.HomeFragmentDirections
import com.example.fooddeliveryapp.ui.viewmodels.HomeViewmodel
import com.example.fooddeliveryapp.util.go

class FoodAdapter(var mContext:Context, var foodsList:List<Foods>, var viewModel:HomeViewmodel)
    :RecyclerView.Adapter<FoodAdapter.CardDesignHolder>()
{
        inner class CardDesignHolder(var binding:CardDesignBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignHolder {
        val binding : CardDesignBinding
        = DataBindingUtil.inflate(LayoutInflater.from(mContext),R.layout.card_design,parent,false)
        return CardDesignHolder(binding)
    }

    override fun onBindViewHolder(holder: CardDesignHolder, position: Int) {
        val food = foodsList.get(position)
        val b = holder.binding
        b.food= food
        b.cardViewFood.setOnClickListener{
            val transition = HomeFragmentDirections.toDetailFragment(food = food)
            Navigation.go(it,transition)
        }

        showImage(food.image,b.imageView)

    }

    override fun getItemCount(): Int {
        return foodsList.size
    }

    fun showImage(imageName:String,imgView:ImageView){
        val url = "http://kasimadalan.pe.hu/foods/images/$imageName"
        Glide.with(mContext).load(url).override(300,300).into(imgView)
    }
}