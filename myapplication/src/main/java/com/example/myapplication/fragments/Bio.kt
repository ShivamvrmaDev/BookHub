package com.example.myapplication.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.RelativeLayout
import android.widget.SearchView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.adapters.Adapter2
import com.example.myapplication.classes.BiographyBook
import com.google.firebase.database.*
import java.util.*

class frag2 :Fragment() {


lateinit var recycle : RecyclerView

lateinit var list :ArrayList<BiographyBook>
    lateinit var list2 :ArrayList<BiographyBook>
lateinit var progress : RelativeLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        var view= inflater.inflate(R.layout.fragment_frag2, container, false)
        progress=view.findViewById(R.id.progresslaooutt)
        progress.visibility=View.VISIBLE
        setHasOptionsMenu(true)
        list = ArrayList()
 list2 = ArrayList()

recycle=view.findViewById(R.id.recycler2)


retrived()

        return view

    }

    private fun retrived() {
      val mref=FirebaseDatabase.getInstance("https://fir-a141a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference("Book")

        mref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {

                if (snapshot.exists()) {

                    for (snap in snapshot.children) {
                        var data = snap.getValue(BiographyBook::class.java)!!

                        list.add(data)
                    }
                    list2.addAll(list)
                }
                progress.visibility=View.GONE
                recycle.adapter = Adapter2(list2,activity as Context)
                recycle.layoutManager = LinearLayoutManager(context)
            }


            override fun onCancelled(error: DatabaseError) {
            }
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
      inflater.inflate(R.menu.icon,menu)
        var search= menu.findItem(R.id.search)
        var s=search.actionView as SearchView
        s.onActionViewExpanded()
        s.focusable
        s.requestFocus()
        s.requestFocusFromTouch()
        s.queryHint="enter book name"
        s.setOnQueryTextListener(object:SearchView.OnQueryTextListener{


            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(p0: String?): Boolean {



                list2.clear()
                var text=p0?.lowercase(Locale.getDefault())

                if (text != null) {
                list.forEach {

                        if (it.name?.lowercase(Locale.getDefault())?.contains(text) == true){

                            list2.add(it)



                        }
                    }
                    recycle.adapter?.notifyDataSetChanged()
                }

                else{
                    list2.clear()
                    list2.addAll(list)
                    recycle.adapter?.notifyDataSetChanged()


                }
               return true
            }
            override fun onQueryTextSubmit(p0: String?): Boolean {



                return true
            }

        })

        super.onCreateOptionsMenu(menu, inflater)
    }


}




