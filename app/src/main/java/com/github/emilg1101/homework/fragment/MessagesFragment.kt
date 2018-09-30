package com.github.emilg1101.homework.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.emilg1101.homework.R

class MessagesFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_messages, container, false)
    }

    override val title: Int
        get() = R.string.nav_title_messages

    override val contentLayout: Int
        get() = R.layout.fragment_messages

    companion object {
        @JvmStatic
        fun newInstance() = MessagesFragment()
    }
}
