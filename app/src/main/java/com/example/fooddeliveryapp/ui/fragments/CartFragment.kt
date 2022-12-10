package com.example.fooddeliveryapp.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentCartBinding
import com.example.fooddeliveryapp.ui.adapter.FoodAdapter
import com.example.fooddeliveryapp.ui.adapter.FoodCartAdapter
import com.example.fooddeliveryapp.ui.viewmodels.CartViewmodel
import com.example.fooddeliveryapp.ui.viewmodels.HomeViewmodel
import com.example.fooddeliveryapp.util.go
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private lateinit var binding : FragmentCartBinding
    private lateinit var viewModel:CartViewmodel
    val currentUser = FirebaseAuth.getInstance().currentUser?.email.toString()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_cart, container, false)

        viewModel.foodCartList.observe(viewLifecycleOwner){
            val adapter = FoodCartAdapter(requireContext(),it,viewModel)

            binding.btnComplete.setOnClickListener {
                for(item in adapter.foodsCartList){
                  Log.e("Result item ","${item.cartId}  ${item.name}   ${item.userName}")
                    viewModel.deleteFood(item.cartId,item.userName)

                }

            }
            binding.adapterCart = adapter
        }


        binding.backBtnCartView.setOnClickListener {
            returnHome(it)
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tmpviewModel : CartViewmodel by viewModels()
        viewModel=tmpviewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFoodsCart(currentUser)
    }

    fun returnHome(imgView:View){
//        Log.e("Result home",imgView.toString())
        Navigation.go(imgView,R.id.to_homeFromCart)
    }

}