package com.elyeproj.rxstate.view

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.rxstate.MainActivity
import com.elyeproj.rxstate.R
import com.elyeproj.rxstate.coordinator.MainCoordinator
import com.elyeproj.rxstate.coordinator.ViewPresentation
import com.elyeproj.rxstate.model.DataModel
import kotlinx.android.synthetic.main.view_success.*

class SuccessFragment : Fragment(), ViewPresentation {

    lateinit var dataModel: DataModel
    override lateinit var coordinator: MainCoordinator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_success, container, false)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        arguments?.let {
            val data = it.getSerializable(MainActivity.PRESENTATION_STATE) as SuccessPresentation.State
            dataModel = data.successData
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        txt_data.text = dataModel.dataString
        container.setOnClickListener { coordinator.toast("Success Fragment") }
    }
}
