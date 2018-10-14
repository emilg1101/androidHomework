package com.github.emilg1101.homework

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.github.emilg1101.homework.fragment.BaseFragment
import com.github.emilg1101.homework.fragment.DashboardFragment
import com.github.emilg1101.homework.fragment.HomeFragment
import com.github.emilg1101.homework.fragment.NotificationsFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val fragmentHome = HomeFragment.newInstance()
    private val fragmentDashboard = DashboardFragment.newInstance()
    private val fragmentNotifications = NotificationsFragment.newInstance()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                setFragment(fragmentHome)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                setFragment(fragmentDashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                setFragment(fragmentNotifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(fragmentHome)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private fun setFragment(fragment: BaseFragment) {
        supportActionBar?.title = getString(fragment.title)
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(wrapper.id, fragment, "").commit()
    }
}
