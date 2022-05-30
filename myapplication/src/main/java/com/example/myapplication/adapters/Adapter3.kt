package com.example.myapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.sqlite.Books
import com.squareup.picasso.Picasso

class Adapter3(var list: List<Books>)  :RecyclerView.Adapter<Adapter3.Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.adapter3,parent,false)

        return Viewholder((view))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {




        var id=list[position]
        holder.author.text=list[position].writer
        holder.year.text=list[position].name
        holder.rating.text=list[position].rating
        holder.price.text=list[position].price



        Picasso.get().load(id.image).into(holder.image)

    }

    override fun getItemCount() :Int{
        return list.size

    }


    class Viewholder(view: View) : RecyclerView.ViewHolder(view) {


        var year = view.findViewById<TextView>(R.id.name)
        var author = view.findViewById<TextView>(R.id.author)
        var rating = view.findViewById<TextView>(R.id.rating)
        var price = view.findViewById<TextView>(R.id.price)
        var image = view.findViewById<ImageView>(R.id.image)
        var layout: RelativeLayout = view.findViewById(R.id.adapter)
    }

}

