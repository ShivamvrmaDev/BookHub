package com.example.myapplication.viewpagers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout


class favpagerr : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view=inflater.inflate(R.layout.fragment_viewpager2, container, false)

        var pager=view.findViewById<ViewPager>(R.id.view)

        var tab=view.findViewById<TabLayout>(R.id.tab)

        pager.adapter= FavPager(childFragmentManager)
        tab.setupWithViewPager(pager)


        return view
    }



}