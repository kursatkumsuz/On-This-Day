package com.kursatkumsuz.onthisday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kursatkumsuz.onthisday.R
import com.kursatkumsuz.onthisday.adapter.RecyclerViewAdapter
import com.kursatkumsuz.onthisday.databinding.FragmentFeedBinding
import com.kursatkumsuz.onthisday.model.PostModel
import com.kursatkumsuz.onthisday.viewmodel.FeedViewModel


class FeedFragment : Fragment() {


    private var _binding: FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private var db = Firebase.firestore
    private val dataList = ArrayList<PostModel>()
    private  var adapter = RecyclerViewAdapter(arrayListOf())
    private lateinit var viewModel: FeedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToUploadFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        //View Model
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        viewModel.getData()
        observeLiveData()
        binding.recyclerView.adapter = adapter

    }

    private fun observeLiveData() {
        viewModel.dataList.observe(viewLifecycleOwner , Observer { post ->
            post?.let {
                println("Data $post")
                dataList.addAll(post)
                adapter = RecyclerViewAdapter(dataList)
            }
        })
    }
}

