package com.example.myapplication


import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room


class Favourites : Fragment() {

    lateinit var recycle :RecyclerView
lateinit var list: List<Books>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      val view =inflater.inflate(R.layout.fragment_favourites, container, false)

        recycle=view.findViewById(R.id.recyclerfav)
        recycle.layoutManager=GridLayoutManager(activity ,2)

        list=Async(activity as Context).execute().get()
        if (activity !=null){

            recycle.layoutManager=GridLayoutManager(activity ,2)
            recycle.adapter=Adapter3(list)

        }





        return view
    }
    class Async( val context: Context) : AsyncTask<Void, Void, List<Books>>() {
        override fun doInBackground(vararg p0: Void?): List<Books> {

            var db= Room.databaseBuilder(context,DbClass::class.java,"books_db").build()
            return db.daomethods().read()
        }
    }

    }