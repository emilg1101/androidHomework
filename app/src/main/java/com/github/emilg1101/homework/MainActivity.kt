package com.github.emilg1101.homework

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.github.emilg1101.homework.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*

class MainActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        EditDialog.EditListener {

    private val profileFragment = ProfileFragment.newInstance()
    private val friendsFragment = FriendsFragment.newInstance()
    private val messagesFragment = MessagesFragment.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        setFragment(profileFragment)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile -> {
                setFragment(profileFragment)
            }
            R.id.nav_friends -> {
                setFragment(friendsFragment)
            }
            R.id.nav_messages -> {
                setFragment(messagesFragment)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun setFragment(fragment: BaseFragment) {
        supportActionBar?.title = getString(fragment.title)
        supportFragmentManager.beginTransaction()
                .replace(container.id, fragment)
                .commit()
    }

    override fun onInfoEdited(login: String, email: String) {
        profileFragment.setInfo(login, email)
        nav_view.text_login.text = login
        nav_view.text_email.text = email
    }
}
