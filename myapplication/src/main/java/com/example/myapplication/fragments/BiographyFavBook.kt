package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.adapters.adapter4
import com.example.myapplication.sqlite.Biography
import com.example.myapplication.sqlite.DbClass

class BiographyFavBook : Fragment() {

    lateinit var list: List<Biography>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_favourites, container, false)

    var    recycle=view.findViewById<RecyclerView>(R.id.recyclerfav)
        recycle.layoutManager= GridLayoutManager(activity ,2)

        list= Async(activity as Context).execute().get()
        if (activity !=null){

            recycle.layoutManager= GridLayoutManager(activity ,2)
            recycle.adapter= adapter4(list)

        }





        return view
    }
    class Async(@SuppressLint("StaticFieldLeak") val context: Context) : AsyncTask<Void, Void, List<Biography>>() {
        override fun doInBackground(vararg p0: Void?): List<Biography> {

            var db= Room.databaseBuilder(context, DbClass::class.java,"books_db").build()
            return db.daomethods().readbio()
        }
    }

}
