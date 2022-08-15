package com.kursatkumsuz.onthisday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.kursatkumsuz.onthisday.R
import com.kursatkumsuz.onthisday.databinding.FragmentSingInBinding


class SingInFragment : Fragment() {

    private var _binding : FragmentSingInBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentSingInBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateSignUpFragment()
    }

    private fun navigateSignUpFragment() {
        binding.navigateToSingUpFragmentBtn.setOnClickListener {
            val action = SingInFragmentDirections.actionSingInFragmentToSingUpFragment()
            Navigation.findNavController(it).navigate(action)
        }

    }
}