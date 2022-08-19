package com.kursatkumsuz.onthisday.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kursatkumsuz.onthisday.R
import com.kursatkumsuz.onthisday.adapter.RecyclerViewAdapter
import com.kursatkumsuz.onthisday.databinding.FragmentFeedBinding
import com.kursatkumsuz.onthisday.model.PostModel


class FeedFragment : Fragment() {


    private var _binding : FragmentFeedBinding? = null
    private val binding get() = _binding!!
    private var db = Firebase.firestore
    private val dataList = ArrayList<PostModel>()
    private lateinit var adapter : RecyclerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFeedBinding.inflate(layoutInflater , container , false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addButton.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToUploadFragment()
            Navigation.findNavController(it).navigate(action)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        val data = db.collection("posts")

        data.addSnapshotListener { value, error ->
            if(error != null) {
                Toast.makeText(context , error.localizedMessage , Toast.LENGTH_LONG).show()
            }
            if (value != null) {
              for (v in value) {
                  val post = v.get("post")
                  val data = PostModel(post.toString())
                  dataList.add(data)
                  adapter = RecyclerViewAdapter(dataList)
                  binding.recyclerView.adapter = adapter
              }
            } else {
                println("Data : Null")
            }
        }
    }


}