package com.guoj.store.ui.activity

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.LabelVisibilityMode.LABEL_VISIBILITY_LABELED
import com.guoj.store.R
import com.guoj.store.base.BaseActivity
import com.guoj.store.databinding.ActivityMainBinding
import com.guoj.store.ui.fragment.DiscountsFragment
import com.guoj.store.ui.fragment.HomeFragment
import com.guoj.store.ui.fragment.RecommendFragment
import com.guoj.store.ui.fragment.SearchFragment

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var recommendFragment: RecommendFragment
    private lateinit var discountsFragment: DiscountsFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var mfm: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
    }

    override fun initPresenter() {

    }

    override fun getLayoutResId(): View {
        return binding.root
    }

    override fun initView() {
        initFragments()
        binding.bottomNavigationview.apply {
            labelVisibilityMode = LABEL_VISIBILITY_LABELED
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.home -> {
                        switchFragment(homeFragment)
                        true
                    }
                    R.id.recommend -> {
                        switchFragment(recommendFragment)
                        true
                    }
                    R.id.discount -> {
                        switchFragment(discountsFragment)
                        true
                    }
                    else -> {
                        switchFragment(searchFragment)
                        true
                    }
                }
            }
        }
    }

    override fun initEvent() {
        initListener()
    }

    private fun initListener() {
        
    }

    private var lastfragment: Fragment? = null

    private fun switchFragment(targetfragment: Fragment) {
        if (targetfragment == lastfragment) return
        val fragmentTransaction = mfm.beginTransaction()
        if (!targetfragment.isAdded) fragmentTransaction.add(
            R.id.main_page_container,
            targetfragment
        ) else fragmentTransaction.show(targetfragment)
        lastfragment?.let {
            fragmentTransaction.hide(it)
        }
        lastfragment=targetfragment
        fragmentTransaction.commit()
    }

    private fun initFragments() {
        homeFragment = HomeFragment()
        recommendFragment = RecommendFragment()
        discountsFragment = DiscountsFragment()
        searchFragment = SearchFragment()
        mfm = supportFragmentManager
        switchFragment(homeFragment)
    }
}