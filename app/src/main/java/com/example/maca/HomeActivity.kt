package com.example.maca

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.maca.fragment.AccountFragment
import com.example.maca.fragment.BooksFragment
import com.example.maca.fragment.ExploreFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        goToBooks(getString(R.string.books))
    }
    fun goToBooks(title:String){
        supportActionBar?.title = title
        val mFragmentManager = supportFragmentManager
        val myFragment = BooksFragment()
        val fragment =
            mFragmentManager.findFragmentByTag(BooksFragment::class.java.simpleName)
        if (fragment !is BooksFragment) {
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, myFragment, BooksFragment::class.java.simpleName)
                .commit()
        }
    }
    fun goToExplore(title:String){
        supportActionBar?.title = title
        val mFragmentManager = supportFragmentManager
        val myFragment = ExploreFragment()
        val fragment =
            mFragmentManager.findFragmentByTag(ExploreFragment::class.java.simpleName)
        if (fragment !is ExploreFragment) {
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, myFragment,
                    ExploreFragment::class.java.simpleName)
                .commit()
        }
    }
    fun goToAccount(title:String){
        supportActionBar?.title = title
        val mFragmentManager = supportFragmentManager
        val myFragment = AccountFragment()
        val fragment =
            mFragmentManager.findFragmentByTag(AccountFragment::class.java.simpleName)
        if (fragment !is AccountFragment) {
            mFragmentManager
                .beginTransaction()
                .replace(R.id.frame_container, myFragment,
                    AccountFragment::class.java.simpleName)
                .commit()
        }
    }
    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            val fragment: Fragment
            val bundle: Bundle
            when (item.itemId) {
                R.id.nav_books-> {
                    goToBooks(getString(R.string.books))
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_explore -> {
                    goToExplore("Bound Service")
                    return@OnNavigationItemSelectedListener true
                }
                R.id.nav_account -> {
                    goToAccount("Media Player Service")
                    return@OnNavigationItemSelectedListener true
                }
            }
            false
        }
}
