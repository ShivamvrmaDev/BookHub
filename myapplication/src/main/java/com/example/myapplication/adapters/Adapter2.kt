package com.example.myapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.activity.InsideBioBook
import com.example.myapplication.classes.BiographyBook
import com.squareup.picasso.Picasso

class Adapter2(var a: ArrayList<BiographyBook>, var context : Context) : RecyclerView.Adapter<Adapter2.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
       var view=LayoutInflater.from(parent.context).inflate(R.layout.adapter2,parent,false)
        return Holder(view)

    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var id=a[position]

        holder.author.text=a[position].writer
        holder.year.text=a[position].name
        holder.rating.text=a[position].rating
        holder.price.text=a[position].price
Picasso.get().load(id.image).into(holder.image)

        holder.layout.setOnClickListener {

            var intent=Intent(context, InsideBioBook::class.java)

intent.putExtra("id",id.id)
            context.startActivity(intent)

        }

    }

    override fun getItemCount(): Int {

        return a.size

    }


    class Holder(view : View) :RecyclerView.ViewHolder(view){
        var year=view.findViewById<TextView>(R.id.name)
        var author=view.findViewById<TextView>(R.id.author)
        var rating=view.findViewById<TextView>(R.id.rating)
        var price=view.findViewById<TextView>(R.id.price)
        var image=view.findViewById<ImageView>(R.id.image)
        var layout: RelativeLayout =view.findViewById(R.id.adapter)


    }
}