package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter3(var list: List<Books>)  :RecyclerView.Adapter<Adapter3.Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {

        var view=LayoutInflater.from(parent.context).inflate(R.layout.adapter3,parent,false)

        return Viewholder((view))
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {




        var id=list[position]
        var auth=list[position]                      /*  now i am parsing the data from the ada array */
        holder.author.text=auth.writer
        var nam=list[position]
        holder.year.text=nam.name

        var rate=list[position]
        holder.rating.text=rate.rating
        var pri=list[position]
        holder.price.text=pri.price



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

