package com.example.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.myapplication.R
import com.example.myapplication.viewpagers.Homee
import com.google.android.material.tabs.TabLayout

class frag1 : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
           val view=inflater.inflate(R.layout.fragment_frag1, container, false)
           var viewPager=view.findViewById<ViewPager>(R.id.viewp)
            var tab=view.findViewById<TabLayout>(R.id.view)
 viewPager.adapter= Homee(childFragmentManager)



tab.setupWithViewPager(viewPager)
            return view
        }



}
