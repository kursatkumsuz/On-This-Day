package com.kursatkumsuz.onthisday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.google.firebase.Timestamp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kursatkumsuz.onthisday.R
import com.kursatkumsuz.onthisday.databinding.FragmentUploadBinding


class UploadFragment : Fragment() {


    private var _binding: FragmentUploadBinding? = null
    private val binding get() = _binding!!
    private var db = Firebase.firestore
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUploadBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        uploadPost()
    }

    private fun uploadPost() {
        binding.shareButton.setOnClickListener { view ->
            val postText = binding.postEditText.text.toString()
            val data = hashMapOf(
                "post" to postText,
                "time" to Timestamp.now()
            )

            db.collection("posts").add(data).addOnSuccessListener {
                Toast.makeText(context, "Shared Post", Toast.LENGTH_LONG).show()
                // Navigate -> FeedFragment
                val action = UploadFragmentDirections.actionUploadFragmentToFeedFragment()
                Navigation.findNavController(view).navigate(action)
            }.addOnFailureListener {
                Toast.makeText(context, it.localizedMessage, Toast.LENGTH_LONG).show()
            }

        }

    }
}