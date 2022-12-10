package com.example.fooddeliveryapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.fooddeliveryapp.MainActivity
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {
    private lateinit var binding :FragmentLoginBinding
    private lateinit var firebaseAuth : FirebaseAuth
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding  = FragmentLoginBinding.inflate(layoutInflater,container,false)
        firebaseAuth = FirebaseAuth.getInstance()
        binding.btnSignIn.setOnClickListener {
            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(requireActivity(), MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        Snackbar.make(requireActivity().findViewById(R.id.loginConstraint),
                            "User or Password is incorrect!",
                            Snackbar.LENGTH_SHORT).show()

                    }
                }
            } else {
                Snackbar.make(requireActivity().findViewById(R.id.loginConstraint),
                    "Empty Fields Are not Allowed !!",Snackbar.LENGTH_SHORT).show()


            }
        }


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if(firebaseAuth.currentUser != null){
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }
    }

}