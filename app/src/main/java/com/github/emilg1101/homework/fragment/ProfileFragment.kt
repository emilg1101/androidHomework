package com.github.emilg1101.homework.fragment

import android.os.Bundle
import android.view.View
import com.github.emilg1101.homework.R
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : BaseFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_edit.setOnClickListener { EditDialog().show(fragmentManager, "edit") }
    }

    override val title: Int
        get() = R.string.nav_title_profile

    override val contentLayout: Int
        get() = R.layout.fragment_profile

    fun setInfo(login: String, email: String) {
        text_email.text = email
        text_login.text = login
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileFragment()
    }
}
