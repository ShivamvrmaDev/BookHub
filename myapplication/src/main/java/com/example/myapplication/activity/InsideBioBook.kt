package com.example.myapplication.activity

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.myapplication.R
import com.example.myapplication.sqlite.Biography
import com.example.myapplication.sqlite.DbClass
import com.google.firebase.database.*
import com.squareup.picasso.Picasso

class InsideBioBook : AppCompatActivity() {



    var id : String="jkgjeg"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout._inside_bio_book)
        id=intent.getStringExtra("id").toString(
        )
        var btn=findViewById<Button>(R.id.button)
        var  name = findViewById<TextView>(R.id.name)
        var  name2 = findViewById<TextView>(R.id.name2)
        var  rating = findViewById<TextView>(R.id.rating)
        var  img = findViewById<ImageView>(R.id.firstimg)
        var   data = findViewById<TextView>(R.id.data)

   var toolbar = findViewById<Toolbar>(R.id.toolbar)

   val progrssbar = findViewById<RelativeLayout>(R.id.progress)
        var  price = findViewById<TextView>(R.id.price)
        progrssbar.visibility=View.VISIBLE
        setSupportActionBar(toolbar)
        supportActionBar?.title="books"
        supportActionBar?.setHomeButtonEnabled(true)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



   var     reference=FirebaseDatabase.getInstance("https://fir-a141a-default-rtdb.asia-southeast1.firebasedatabase.app/").reference
        reference.child("Book").child(id).addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
if(snapshot.exists()){
    var a=snapshot.value as Map<*, *>
   progrssbar.visibility= View.GONE
    name.text=a.get("name").toString()
    name2.text=a.get("writer").toString()
    price.text=a.get("price").toString()
    data.text=a.get("desc").toString()
    rating.text=a.get("rating").toString()
    Picasso.get().load(a.get("image").toString()).into(img)
    var image=a.get("image").toString()

    var favriotes= Biography(id.toInt(),
        name.text.toString(),
        name2.text.toString(),
        price.text.toString(),
        data.text.toString(),
        image,
        rating.text.toString())

    var bio= Bioclass(applicationContext,favriotes,1).execute()

    var fav=bio.get()
    if(fav){
        btn.text=getString(R.string.remove_book)
        btn.setBackgroundColor(resources.getColor(R.color.purple_500))

    }else{
        btn.text=getString(R.string.add_book)
        btn.setBackgroundColor(resources.getColor(R.color.purple_200))
    }
    btn.setOnClickListener {
        if (!Bioclass(applicationContext, favriotes, 1).execute().get()) {


            var book = Bioclass(applicationContext, favriotes, 2).execute()
            var result = book.get()
            if (result) {
                btn.text = getString(R.string.remove_book)
                btn.setBackgroundColor(resources.getColor(R.color.purple_500))
                Toast.makeText(this@InsideBioBook, "added to favourites", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this@InsideBioBook, "some error occured", Toast.LENGTH_SHORT).show()
            }
        } else {
            var book = Bioclass(applicationContext, favriotes, 3).execute()
            var result = book.get()
            if (result) {
                btn.text=getString(R.string.add_book)
                btn.setBackgroundColor(resources.getColor(R.color.purple_200))
                Toast.makeText(this@InsideBioBook, "removed from favourites", Toast.LENGTH_SHORT).show()
            } else {

                Toast.makeText(this@InsideBioBook, "some error occured", Toast.LENGTH_SHORT).show()
            }

        }
    }

}
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
    override fun onOptionsItemSelected(item :MenuItem): Boolean {

when(item.itemId){
    android.R.id.home-> onBackPressed()

}
        return super.onOptionsItemSelected(item)
    }


    class Bioclass(context: Context, var biography: Biography?, val mode:Int) :
        AsyncTask<Void, Void, Boolean>(){

        var db= Room.databaseBuilder(context, DbClass::class.java,"books_db").build()
        override fun doInBackground(vararg p0: Void?): Boolean {

            when(mode){

                1->{



                    var read: Biography?=db.daomethods().getBooksbio(biography?.id.toString())
                    db.close()
                    return read != null


                }
                2->{
                    biography?.let { db.daomethods().Insert(it) }
                    db.close()
                    return true

                }
                3->{
                    biography?.let { db.daomethods().Delete(it) }
                    db.close()
                    return true

                }



            }
            return false
        }
    }
}