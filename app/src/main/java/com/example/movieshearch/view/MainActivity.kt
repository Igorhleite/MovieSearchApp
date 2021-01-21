package com.example.movieshearch.view


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.movieshearch.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    lateinit var favoriteFragment: FavoriteFragment

    lateinit var searchFragment: SearchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bottomNavigationView : BottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        favoriteFragment = FavoriteFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout,favoriteFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.bottomNavHome -> {
                    favoriteFragment = FavoriteFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,favoriteFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.bottomNavSearch -> {
                    searchFragment =
                        SearchFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frameLayout,searchFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
            }
            true
        }

    }

}