package com.example.myapplication.viewpagers

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.fragments.Dashboard
import com.example.myapplication.fragments.frag2


class Homee(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {

        return 2
    }

    override fun getItem(position: Int): Fragment {

       when(position){


           0-> {
               return Dashboard()
           }

           1-> {
               return frag2()
           }

       else ->{

           return Dashboard()
       }

       }

    }

    override fun getPageTitle(position: Int): CharSequence {
        when(position){
            0 ->{

            return "Books"   }
            1-> {
                return "Biography"
            }

            else ->{
                return "Books"
            }


        }
    }
}