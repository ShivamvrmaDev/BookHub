package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.Toast.makeText
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.squareup.picasso.Picasso



class adapter(var context : Context, var ada : ArrayList<DataHai>) : Adapter<adapter.viewholderclass>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholderclass {
        var view= LayoutInflater.from(parent.context).inflate(R.layout.adapter,parent,false)

        return viewholderclass(view)
    }

    override fun onBindViewHolder(holder: viewholderclass, position: Int) {

        var id=ada[position]
        var auth=ada[position]                      /*  now i am parsing the data from the ada array */
        holder.author.text=auth.author
        var nam=ada[position]
        holder.year.text=nam.name

        var rate=ada[position]
        holder.rating.text=rate.rating
        var pri=ada[position]
        holder.price.text=pri.price



        Picasso.get().load(id.image).into(holder.image)

holder.layout.setOnClickListener {

    var intent =Intent(context,InsideBook :: class.java)
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
