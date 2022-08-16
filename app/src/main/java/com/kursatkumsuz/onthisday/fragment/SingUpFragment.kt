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
import com.kursatkumsuz.onthisday.databinding.FragmentSingUpBinding


class SingUpFragment : Fragment() {


    private var _binding: FragmentSingUpBinding? = null
    private val binding get() = _binding!!
    private lateinit var email: String
    private lateinit var password: String
    private var auth = Firebase.auth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSingUpBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUp()
    }

    private fun signUp() {

        binding.signUpBtn.setOnClickListener { view ->
            email = binding.emailText.text.toString()
            password = binding.passwordText.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener {
                    Toast.makeText(context, "Register is Succesfull", Toast.LENGTH_LONG).show()
                    //Navigate -> SignInFragment
                    val action = SingUpFragmentDirections.actionSingUpFragmentToSingInFragment()
                    Navigation.findNavController(view).navigate(action)
                }.addOnFailureListener {
                    Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
                }

            } else {
                Toast.makeText(context, "Please Enter E-Mail and Password", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }

}