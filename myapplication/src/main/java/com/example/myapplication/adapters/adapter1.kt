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
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapplication.R
import com.example.myapplication.activity.InsideBook
import com.example.myapplication.classes.NormalBook
import com.squareup.picasso.Picasso


class adapter1(var context : Context, var ada : ArrayList<NormalBook>) : Adapter<adapter1.viewholderclass>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderclass {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.adapter,parent,false)

        return viewholderclass(view)
    }

    override fun onBindViewHolder(holder: viewholderclass, position: Int) {

        var id=ada[position]
        holder.author.text=ada[position].author
        holder.year.text=ada[position].name
        holder.rating.text=ada[position].rating
        holder.price.text=ada[position].price

        Picasso.get().load(id.image).into(holder.image)
holder.layout.setOnClickListener {

    var intent =Intent(context, InsideBook :: class.java)
intent.putExtra("book_id",id.book_id)
    context.startActivity(intent)
}
    }
    override fun getItemCount() :Int{
        return ada.size             /* returning the size  */
    }
    class viewholderclass(view: View) : RecyclerView.ViewHolder(view) {

        var year=view.findViewById<TextView>(R.id.name)
        var author=view.findViewById<TextView>(R.id.author)
        var rating=view.findViewById<TextView>(R.id.rating)
        var price=view.findViewById<TextView>(R.id.price)
        var image=view.findViewById<ImageView>(R.id.image)
        var layout: RelativeLayout =view.findViewById(R.id.adapter)





    }
}
