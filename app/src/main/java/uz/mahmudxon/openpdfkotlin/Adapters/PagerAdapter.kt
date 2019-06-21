package uz.mahmudxon.openpdfkotlin.Adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm : FragmentManager, var data : ArrayList<Fragment>) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment
    =    data[position]


    override fun getCount(): Int = data.size
}