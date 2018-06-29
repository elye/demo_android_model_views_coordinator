package com.elyeproj.rxstate.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.rxstate.MainActivity.Companion.PRESENTATION_STATE
import com.elyeproj.rxstate.R
import com.elyeproj.rxstate.coordinator.Presenter
import com.elyeproj.rxstate.coordinator.MainCoordinator
import com.elyeproj.rxstate.coordinator.Presentation
import kotlinx.android.synthetic.main.view_error.*

class ErrorFragment: Fragment(), Presenter {

    lateinit var errorMessage: String
    override lateinit var coordinator: MainCoordinator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_error, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.let{
            val data = it.getSerializable(PRESENTATION_STATE) as ErrorPresentation.State
            errorMessage = data.errorMessage
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_error.text = errorMessage
        container.setOnClickListener { coordinator.toast("Error Fragment") }
    }
}
