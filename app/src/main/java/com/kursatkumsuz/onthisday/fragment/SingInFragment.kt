package com.kursatkumsuz.onthisday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kursatkumsuz.onthisday.R
import com.kursatkumsuz.onthisday.databinding.FragmentSingInBinding


class SingInFragment : Fragment() {

    private var _binding: FragmentSingInBinding? = null
    private val binding get() = _binding!!
    private var auth = Firebase.auth
    private lateinit var email: String
    private lateinit var password: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateSignUpFragment()
        signIn()
    }


    private fun signIn() {
        binding.signUpBtn.setOnClickListener { view ->

            email = binding.emailText.text.toString()
            password = binding.passwordText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password).addOnSuccessListener {
                    // Navigate -> FeedFragment
                    val action = SingInFragmentDirections.actionSingInFragmentToFeedFragment()
                    Navigation.findNavController(view).navigate(action)

                }.addOnFailureListener {
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(context, "Please Enter Email and Password", Toast.LENGTH_LONG).show()
            }

        }

    }

    private fun navigateSignUpFragment() {
        binding.navigateToSingUpFragmentBtn.setOnClickListener {
            val action = SingInFragmentDirections.actionSingInFragmentToSingUpFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
}