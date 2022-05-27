package com.example.myapplication

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Entity
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity







        import android.os.Bundle
import android.view.MenuItem

import android.view.View
                import android.widget.*
import android.widget.Toast.LENGTH_SHORT
import android.widget.Toast.makeText
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import androidx.room.RoomDatabase

import com.android.volley.Response
                import com.android.volley.toolbox.JsonObjectRequest
                import com.android.volley.toolbox.Volley
                import com.google.gson.Gson
                import com.squareup.picasso.Picasso
                import org.json.JSONObject


class InsideBook : AppCompatActivity() {
lateinit var add :Button
            lateinit var toolbar: Toolbar
            lateinit var name: TextView
            lateinit var name2: TextView
            lateinit var price: TextView
            lateinit var rating: TextView
            lateinit var about: TextView
            lateinit var data: TextView

            lateinit var progrssbar: RelativeLayout
            lateinit var firstimg: ImageView


            var bookid: String? = "100"
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.inside_book)

                toolbar = findViewById(R.id.toolbar)
                name = findViewById(R.id.name)
                name2 = findViewById(R.id.name2)
                rating = findViewById(R.id.rating)
                about = findViewById(R.id.data)
                data = findViewById(R.id.desc)
                add = findViewById(R.id.button)
                progrssbar = findViewById(R.id.progressb)
                firstimg = findViewById(R.id.firstimg)
                price = findViewById(R.id.price)
toolbar()

                progrssbar.visibility= View.VISIBLE



                if (intent != null) {
                    bookid = intent.getStringExtra("book_id")
                } else {
                    finish()
                    makeText(this, "error", Toast.LENGTH_LONG).show()

                }

                var que = Volley.newRequestQueue(this)

                var url = "http://13.235.250.119/v1/book/get_book/"
                val par = JSONObject()
                par.put("book_id", bookid)

                var jsonobj = @SuppressLint("SetTextI18n")
                object : JsonObjectRequest(Method.POST, url, par, Response.Listener {


                    val success = it.getBoolean("success")
                    if (success) {
                        var json = it.getJSONObject("book_data")
                        progrssbar.visibility= View.GONE
                        Picasso.get().load(json.getString("image")).into(firstimg)
                        name.text = json.getString("name")
                        name2.text = json.getString("author")
                        price.text = json.getString("price")
                        rating.text = json.getString("rating")
                        about.text = json.getString("description")
var image=json.getString("image")

                    var book =Books(
                        bookid?.toInt() as Int ,
                         name.text.toString(),
                        name2.text.toString(),
                        price.text.toString(),
                        about.text.toString(),
                        image,
                        rating.text.toString(),)
                        var dataclass=dataclass(applicationContext,book,1).execute()

                var fav=dataclass.get()
                        if(fav){


                            add.setBackgroundColor(resources.getColor(R.color.purple_500))
                            add.text=getString(R.string.remove_book)
                        }else{

                            add.setBackgroundColor(resources.getColor(R.color.purple_200))
                            add.text=getString(R.string.add_book)
                        }
                        add.setOnClickListener {
                            if (!dataclass(applicationContext, book, 1).execute().get()) {


                                var book = dataclass(applicationContext, book, 2).execute()
                                var result = book.get()
                                if (result) {
                                    add.setBackgroundColor(resources.getColor(R.color.purple_500))
                                    add.text=getString(R.string.remove_book)

                                    makeText(this, "added to favourites", LENGTH_SHORT).show()
                                } else {

                                    makeText(this, "some error occured", LENGTH_SHORT).show()
                                }
                            } else {
                                var book = dataclass(applicationContext, book, 3).execute()
                                var result = book.get()
                                if (result) {
                                    add.setBackgroundColor(resources.getColor(R.color.purple_200))
                                    add.text=getString(R.string.add_book)

                                    makeText(this, "removed from favourites", LENGTH_SHORT).show()
                                } else {

                                    makeText(this, "some error occured", LENGTH_SHORT).show()
                                }

                            }
                        }


                    } else {
                        makeText(this, "dcfjhrhbrjhh", Toast.LENGTH_LONG).show()
                    }

                }, Response.ErrorListener {

                    makeText(this, "some error mnnzbdhboccured", Toast.LENGTH_SHORT)
                        .show()
                }) {
                    override fun getHeaders(): MutableMap<String, String> {
                        var hash = HashMap<String, String>()
                        hash["content-type"] = "application/json"
                        hash["token"] = "9bf534118365f1"
                        return hash
                    }
                }
                que.add(jsonobj)


                add.setOnClickListener {



                }
            }

    fun toolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title="inside book"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)




    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            android.R.id.home -> onBackPressed()

        }
        return super.onOptionsItemSelected(item)
    }

    class dataclass(context: Context,var books: Books?,val mode:Int) :AsyncTask<Void,Void,Boolean>(){

var db= Room.databaseBuilder(context,DbClass::class.java,"books_db").build()
        override fun doInBackground(vararg p0: Void?): Boolean {

            when(mode){

             1->{



                 var read: Books?=db.daomethods().getBooks(books?.id.toString())
                 db.close()
                 return read != null


             }
                2->{
                    books?.let { db.daomethods().Insert(it) }
                    db.close()
                    return true

                }
                3->{
                    books?.let { db.daomethods().Delete(it) }
                    db.close()
                    return true

                }



            }
return false
        }
    }



        }


