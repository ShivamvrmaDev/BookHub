package com.example.myapplication.fragments


import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.*
import android.widget.RelativeLayout
import android.widget.SearchView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.R
import com.example.myapplication.adapters.adapter1
import com.example.myapplication.classes.NormalBook
import com.example.myapplication.classes.connection
import org.json.JSONException
import java.util.*

class Dashboard : Fragment() {




    lateinit var swipe: SwipeRefreshLayout
    lateinit var value :ArrayList<NormalBook>
    lateinit var temp :ArrayList<NormalBook>
    lateinit var recyclerView :RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?

    ): View? {
        var view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        temp= ArrayList()
        value=ArrayList()
        setHasOptionsMenu(true)
        swipe = view.findViewById(R.id.swipe)
        var    relativeLayout = view.findViewById<RelativeLayout>(R.id.progresslaoout)
        recyclerView = view.findViewById(R.id.recycler)
        var LayoutManager = LinearLayoutManager(activity)
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


                    var success = it.getBoolean("success")
                    if (success) {
                        var data = it.getJSONArray("data")

                        for (i in 0 until  data.length())    //compiler yhi bolta h ki tum null aray ki length le rhe ho
                        {
                            var jsonobject = data.getJSONObject(i)

                            var  values=  NormalBook (
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
                    relativeLayout.visibility = View.GONE
                    recyclerView.adapter = adapter1(activity as Context, temp)
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

        if (connection().ConnectionChecking(activity as Context)) {

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
