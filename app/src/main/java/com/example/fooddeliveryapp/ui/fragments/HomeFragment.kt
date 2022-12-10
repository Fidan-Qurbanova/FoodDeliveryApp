package com.example.fooddeliveryapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.RegistrationActivity
import com.example.fooddeliveryapp.data.entity.Foods
import com.example.fooddeliveryapp.databinding.FragmentHomeBinding
import com.example.fooddeliveryapp.ui.adapter.FoodAdapter
import com.example.fooddeliveryapp.ui.viewmodels.HomeViewmodel
import com.example.fooddeliveryapp.util.go
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding
    private lateinit var viewModel: HomeViewmodel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(layoutInflater,R.layout.fragment_home, container, false)
        binding.homeFragment=this
        //binding.rv.layoutManager = GridLayoutManager(requireContext(),2,)
        adapterSet("All")
        //binding.rv.layoutManager=StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
       // binding.adapter.notifyDataSetChanged()
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel :HomeViewmodel by viewModels()
        viewModel=tempViewModel

    }

    fun filterList(selectedCategory:String,foodLists:List<Foods>):List<Foods>{
        var filterFoodList = ArrayList<Foods>()
        if(selectedCategory.equals("All")) {
            Log.e("filterList All",filterFoodList.size.toString())
            return foodLists
        }   //if all category selected

        for(food in foodLists){
            if(food.category.lowercase().contains(selectedCategory.toLowerCase())){
                Log.e("filterList food","$food.category  $selectedCategory" )
                filterFoodList.add(food)
            }
        }
        return filterFoodList
    }

    fun adapterSet(selectedCategory: String) {
        viewModel.foodList.observe(viewLifecycleOwner){
            //val adapter = FoodAdapter(requireContext(),it,viewModel)
            val adapter = FoodAdapter(requireContext(),filterList(selectedCategory,it),viewModel)
            Log.e("Result home",adapter.foodsList.size.toString())
           // adapter.notifyDataSetChanged()
            binding.rv.layoutManager=StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            binding.adapter = adapter
        }
    }

    fun logoutSystem(){
        val fireBaseAuth = FirebaseAuth.getInstance().signOut()
        val intent = Intent(requireActivity(), RegistrationActivity::class.java)
        requireActivity().startActivity(intent)    }


}