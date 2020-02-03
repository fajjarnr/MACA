package com.example.maca.test

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.maca.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val posts: ArrayList<String> = ArrayList()

        for(i in 1..100){
            posts.add("Post # $i")
        }

        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = PostsAdapter(posts)
    }
}
