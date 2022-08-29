package com.kursatkumsuz.onthisday.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kursatkumsuz.onthisday.adapter.RecyclerViewAdapter
import com.kursatkumsuz.onthisday.model.PostModel

class FeedViewModel : ViewModel() {

    var dataList = MutableLiveData<ArrayList<PostModel>>()
    private var db = Firebase.firestore


    fun getData() {
        val data = db.collection("posts").orderBy("time", Query.Direction.ASCENDING)
        data.addSnapshotListener { value, error ->
            if (error != null) {
                //Toast.makeText(context, error.localizedMessage, Toast.LENGTH_LONG).show()
            }
            if (value != null) {
                for (v in value) {
                    val post = v.get("post")
                    val myData = PostModel(post.toString())
                    dataList.value = arrayListOf(myData)
                }
            } else {
                println("Data : Null")
            }
        }
    }
}