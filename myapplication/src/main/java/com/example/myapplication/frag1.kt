package com.example.myapplication

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment

import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class frag1 : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
           val view=inflater.inflate(R.layout.fragment_frag1, container, false)
           var viewPager=view.findViewById<ViewPager>(R.id.viewp)
            var tab=view.findViewById<TabLayout>(R.id.view)
 viewPager.adapter=Viewpager(childFragmentManager)



tab.setupWithViewPager(viewPager)
            return view
        }



}
