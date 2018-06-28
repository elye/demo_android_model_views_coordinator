package com.elyeproj.rxstate.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.rxstate.R
import com.elyeproj.rxstate.coordinator.Presenter
import com.elyeproj.rxstate.coordinator.MainCoordinator
import com.elyeproj.rxstate.coordinator.Presentation
import kotlinx.android.synthetic.main.view_error.*

class ErrorFragment: Fragment(), Presenter {
    companion object {
        const val ERROR_STATE = "ErrorState"
    }

    lateinit var errorMessage: String
    override lateinit var coordinator: MainCoordinator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_error, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            errorMessage = it.getString(ERROR_STATE)
        }
        txt_error.text = errorMessage
        container.setOnClickListener { coordinator.toast("Error Fragment") }
    }

    override fun setData(presentable: Presentation) {
        errorMessage = (presentable as ErrorPresentation).errorMessage
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(ERROR_STATE, errorMessage)
    }
}
