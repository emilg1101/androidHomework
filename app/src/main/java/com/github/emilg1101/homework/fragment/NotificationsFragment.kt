package com.github.emilg1101.homework.fragment

import android.os.Bundle
import android.view.View
import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.adapter.TabsAdapter
import kotlinx.android.synthetic.main.fragment_notifications.*

class NotificationsFragment : BaseFragment() {

    private val tabOneFragment = TabFragment.newInstance("Tab 1")
    private val tabTwoFragment = TabFragment.newInstance("Tab 2")
    private val tabThreeFragment = TabFragment.newInstance("Tab 3")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(tabOneFragment, tabTwoFragment, tabThreeFragment)
        pager.adapter = TabsAdapter(fragments, childFragmentManager)
        tabs.setupWithViewPager(pager)
    }

    override val title: Int
        get() = R.string.fragment_title_notifications

    override val contentLayout: Int
        get() = R.layout.fragment_notifications

    override val menuRes: Int?
        get() = null

    companion object {
        fun newInstance() = NotificationsFragment()
    }
}
