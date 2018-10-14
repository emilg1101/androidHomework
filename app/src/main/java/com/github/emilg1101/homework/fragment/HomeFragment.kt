package com.github.emilg1101.homework.fragment

import com.github.emilg1101.homework.R

class HomeFragment : BaseFragment() {

    override val title: Int
        get() = R.string.fragment_title_home

    override val contentLayout: Int
        get() = R.layout.fragment_home

    override val menuRes: Int?
        get() = null

    companion object {
        fun newInstance() = HomeFragment()
    }
}
