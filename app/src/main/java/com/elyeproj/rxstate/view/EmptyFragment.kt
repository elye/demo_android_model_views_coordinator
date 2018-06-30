package com.elyeproj.rxstate.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.rxstate.R
import com.elyeproj.rxstate.coordinator.Coordinator
import com.elyeproj.rxstate.coordinator.ViewPresentation
import kotlinx.android.synthetic.main.view_empty.*

class EmptyFragment: Fragment(), ViewPresentation {

    override lateinit var coordinator: Coordinator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_empty, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container.setOnClickListener { coordinator.toast("Empty Fragment") }
    }
}
