package com.example.fooddeliveryapp.ui.fragments

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.bumptech.glide.Glide
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentDetailBinding
import com.example.fooddeliveryapp.ui.viewmodels.DetailViewModel
import com.example.fooddeliveryapp.util.go
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailFragment : Fragment() {
private lateinit var binding:FragmentDetailBinding
private lateinit var viewModel: DetailViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detail, container, false)
        binding.detailFragment=this
        binding.textViewAmount.text="1"
        val bundle:DetailFragmentArgs by navArgs()
        val resultFood = bundle.food
        binding.food = resultFood
        showImage(bundle.food.image,binding.imageView2)

        binding.backBtnVIew.setOnClickListener {
            Navigation.go(it,R.id.to_homeFromDetail)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetailViewModel by viewModels()
        viewModel= tempViewModel

    }

    fun showImage(imageName:String,imgView:ImageView){
        val url = "http://kasimadalan.pe.hu/foods/images/$imageName"
        Glide.with(requireContext()).load(url).override(400,400).into(imgView)
    }

    fun reduceAmount(amount:String):Int{
        var resultAmount = amount.toInt()
        if(resultAmount!=0){
            resultAmount -= 1
        }
        return resultAmount
    }


    fun increaseAmount(amount:String) : Int {
        var resultAmount = amount.toInt()
            resultAmount += 1
        return resultAmount
    }

    fun resultPrice(orderAmount:String,price:Int):Int{
        var amount = orderAmount.toInt()
        if(amount!=0){
            return amount * price
        }
        return price
    }

     fun addFoodCart(name:String, image:String, price:String, category:String, orderAmount:String){
         if(orderAmount.toInt()!=0) {
             val currentUser = FirebaseAuth.getInstance().currentUser?.email.toString()
             var price = TextUtils.substring(price,0,price.length-1)
             viewModel.insertFood(name, image, price.toInt(), category, orderAmount.toInt(), currentUser)
//             Snackbar.make(binding.imageView2,"Added to your cart",Snackbar.LENGTH_SHORT)
         }
    }

    fun reduceIncreaseAmount(imgView : ImageView,price:Int){
        var tempPrice = 0
        when (imgView) {
            binding.imgMinus -> binding.textViewAmount.text =
                reduceAmount(binding.textViewAmount.text.toString()).toString()

            binding.imgPlus -> binding.textViewAmount.text=
                increaseAmount(binding.textViewAmount.text as String).toString()
        }
        tempPrice = resultPrice(binding.textViewAmount.text.toString(),price)
        binding.prdDetailPriceVIew.text= tempPrice.toString() +"₼"
    }

}