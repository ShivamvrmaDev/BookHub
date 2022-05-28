package com.example.myapplication.viewpagers;

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter
import com.example.myapplication.fragments.BiographyFavBook
import com.example.myapplication.fragments.NormalFavBook

class FavPager(fm : FragmentManager) :FragmentPagerAdapter(fm){
 override fun getCount(): Int {
return 2
 }

 override fun getItem(position: Int): Fragment {

    when(position){

     0-> return NormalFavBook()
     1-> return BiographyFavBook()
     else ->{
      return NormalFavBook()
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
