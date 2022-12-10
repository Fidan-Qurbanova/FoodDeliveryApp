package com.example.fooddeliveryapp.ui.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.fooddeliveryapp.R
import com.example.fooddeliveryapp.RegistrationActivity
import com.example.fooddeliveryapp.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class SignUpFragment : Fragment() {
 private lateinit var binding:FragmentSignUpBinding
    private lateinit var fireBaseAuth :FirebaseAuth
    @SuppressLint("SuspiciousIndentation")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)

        fireBaseAuth = FirebaseAuth.getInstance()
        binding.btnSignUp.setOnClickListener {

            val email = binding.emailEt.text.toString()
            val pass = binding.passET.text.toString()
            val confirmPass = binding.confirmPassEt.text.toString()

            if (email.isNotEmpty() && pass.isNotEmpty() && confirmPass.isNotEmpty()) {
                if (pass == confirmPass) {

                    fireBaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(requireActivity(), RegistrationActivity::class.java)
                                requireActivity().startActivity(intent)
//                            val frag = LoginFragment()
//                            val manager: FragmentManager
//                            manager = requireActivity().supportFragmentManager
//                            val transaction: FragmentTransaction = manager.beginTransaction()
//                            transaction.add(R.id.fragmentContainerViewRegist, frag, "Test Fragment")
//                            transaction.commit()



                        } else {
                            Snackbar.make(requireActivity().findViewById(R.id.signUpConstraint),it.exception.toString(),Snackbar.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Snackbar.make(requireActivity().findViewById(R.id.signUpConstraint),"Password is not matching",Snackbar.LENGTH_SHORT).show()
                }
            } else {
                Snackbar.make(requireActivity().findViewById(R.id.signUpConstraint),"Empty Fields Are not Allowed !!",Snackbar.LENGTH_SHORT).show()
            }
        }



        return binding.root
    }
}

