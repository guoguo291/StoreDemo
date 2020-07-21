package com.guoj.store.ui.adpter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.guoj.store.model.bean.Categories
import com.guoj.store.ui.fragment.HomePagerFragment
import com.guoj.store.ui.fragment.SearchFragment
import com.guoj.store.utils.Constants

class HomePageAdapter(fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var categoryList: ArrayList<Categories.Data> = java.util.ArrayList()
    override fun getItem(position: Int): Fragment {

        return HomePagerFragment.newInstance(categoryList.get(position)).apply {
            arguments?.putString(Constants.ID_MATERIAL,categoryList.get(position).id.toString())
            arguments?.putString(Constants.TITLE,categoryList.get(position).title)
        }
    }

    override fun getCount(): Int = categoryList.size

    //设置标题
    override fun getPageTitle(position: Int): CharSequence? {
        return categoryList.get(position).title
    }

    fun setCategoryList(categories: Categories?) {
        if (categories != null) {
            this.categoryList.clear()
            this.categoryList.addAll(categories.data)
            notifyDataSetChanged()
        }
    }
}