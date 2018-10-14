package com.github.emilg1101.homework.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

abstract class BaseFragment : Fragment() {

    abstract val title: Int

    abstract val contentLayout: Int

    abstract val menuRes: Int?

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(contentLayout, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        menuRes?.let { setHasOptionsMenu(true) }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menuRes?.let {
            inflater?.inflate(it, menu)
        }
    }
}
