package com.example.myapplication


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.inputmethodservice.Keyboard

import android.os.Bundle
import android.provider.Settings

import android.view.*

import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException

import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Dashboard : Fragment() {

    lateinit var recyclerView: RecyclerView // recycler view/
    lateinit var relativeLayout: RelativeLayout
    lateinit var ada: adapter  //adapter class object

    lateinit var swipe: SwipeRefreshLayout
    lateinit var LayoutManager: LinearLayoutManager
lateinit var value :ArrayList<DataHai>
    lateinit var temp :ArrayList<DataHai>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View? {
        var view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        temp= ArrayList()
        value=ArrayList()
        setHasOptionsMenu(true)

        swipe = view.findViewById(R.id.swipe)
        relativeLayout = view.findViewById(R.id.progresslaoout)
        recyclerView = view.findViewById(R.id.recycler)
        LayoutManager = LinearLayoutManager(activity)
        gettingData()
        swipe.setOnRefreshListener {
            gettingData()
        }
        relativeLayout.visibility = View.VISIBLE

        val que = Volley.newRequestQueue(activity as Context)
        var url = "http://13.235.250.119/v1/book/fetch_books/"


        var jsonObjectRequest =
            object : JsonObjectRequest(Method.GET, url, null, Response.Listener {

                try {
                    relativeLayout.visibility = View.GONE

                    var success = it.getBoolean("success")
                    if (success) {
                        var data = it.getJSONArray("data")
                        for (i in 0 until data.length())    //compiler yhi bolta h ki tum null aray ki length le rhe ho
                        {
                            var jsonobject = data.getJSONObject(i)
                         var  values=  DataHai (
                              jsonobject.getString("book_id"),
                              jsonobject.getString("name"),
                              jsonobject.getString("author"),
                              jsonobject.getString("price"),
                              jsonobject.getString("rating"),
                              jsonobject.getString("image")
                          )
                            value.add(values)
//

                        }
                        temp.addAll(value)
                    } else {
                        makeText(context, "some error occuhsfbnbred", Toast.LENGTH_SHORT).show()
                    }
//
                    /* saying context == null*/
                    ada = adapter(activity as Context, temp)        /*now giving activity as context
               and adding value array that will access by adapter class array*/
                    recyclerView.adapter = ada          /* connecting these classes  */
                    recyclerView.layoutManager = LayoutManager
                } catch (e: JSONException) {

                    makeText(context, "some error occured", Toast.LENGTH_SHORT).show()
                }
            }, Response.ErrorListener {
                makeText(context, "some error occured", Toast.LENGTH_SHORT).show()
            }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val header = HashMap<String, String>()
                    header.put("value-type", "application/json")
                    header.put("token", " 9bf534118365f1")
                    return header


                }

            }
        que.add(jsonObjectRequest)

        return view
    }

    fun gettingData() {

        if (Connection().ConnectionChecking(activity as Context)) {


        } else {
            var build = AlertDialog.Builder(activity as Context)

            build.setTitle("Not Connected")
            build.setMessage("please check your internet Connection")
            build.setPositiveButton("try again") { text, listner ->

                startActivityForResult(Intent(Settings.ACTION_WIRELESS_SETTINGS), 0)
            }
            build.setNegativeButton("cancel") { text, listner ->

            }

            build.create()
            build.show()
        }


        swipe.isRefreshing = false
    }

    @SuppressLint("NewApi")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.icon,menu)
        var item=menu.findItem(R.id.search)
        var search=item?.actionView as SearchView
        search.onActionViewExpanded()
        search.focusable
        search.requestFocus()
        search.requestFocusFromTouch()
search.queryHint= "enter"


        search.setOnQueryTextListener(object:SearchView.OnQueryTextListener{


            override fun onQueryTextSubmit(p0: String?): Boolean {


              return true
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(p0: String?): Boolean {
                temp.clear()

                var text= p0?.lowercase(Locale.getDefault())
                if (text!!.isNotEmpty()) {

                    value.forEach {
//if(it.lowercase(Locale.getDefault()).contains(text))

                        if (it.name.lowercase(Locale.getDefault()).contains(text)) {
                            temp.add(it)
                        }
                    }
                    recyclerView.adapter?.notifyDataSetChanged()
                }
                else {

                }
                return false
        }
    })
        super.onCreateOptionsMenu(menu, inflater)
}


}
