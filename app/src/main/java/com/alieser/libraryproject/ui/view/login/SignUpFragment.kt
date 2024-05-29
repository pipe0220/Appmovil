package com.alieser.libraryproject.ui.view.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.alieser.libraryproject.R
import com.alieser.libraryproject.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        auth = Firebase.auth

        binding.btnSignUp.setOnClickListener {
            val emailText = binding.etUserNameSignUp.text.toString().trim()
            val passwordText = binding.etPasswordSignUp.text.toString().trim()

            if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
                if (emailText.length >= 2 && passwordText.length >= 2) {
                    auth.createUserWithEmailAndPassword(emailText, passwordText)
                        .addOnSuccessListener {
                            // Success message
                            Navigation.findNavController(binding.root).navigate(R.id.action_signUpFragment_tologInFragment)
                        }
                        .addOnFailureListener {
                            // Failure message
                            Toast.makeText(
                                this@SignUpFragment.requireActivity(),
                                it.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                } else {
                    Toast.makeText(
                        this@SignUpFragment.requireActivity(),
                        "Username and password must be at least 2 characters long",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this@SignUpFragment.requireActivity(),
                    "Please enter email and password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }
}