package com.example.myapplication;

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter

class BioPager(fm : FragmentManager) :FragmentPagerAdapter(fm){
 override fun getCount(): Int {
return 2
 }

 override fun getItem(position: Int): Fragment {

    when(position){

     0-> return Favourites()
     1-> return BioFrag()
     else ->{
      return Favourites()
     }


    }


 }

 override fun getPageTitle(position: Int): CharSequence? {


  when(position){

   0->  return  "Books"
   1-> return "BioGraphy"


   else ->{
    return "Books"
   }
  }


 }


}
