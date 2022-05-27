package com.example.myapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout


class Viewpager2 : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_viewpager2, container, false)

        var pager=view.findViewById<ViewPager>(R.id.view)

        var tab=view.findViewById<TabLayout>(R.id.tab)

        pager.adapter=BioPager(childFragmentManager)
        tab.setupWithViewPager(pager)


        return view
    }



}