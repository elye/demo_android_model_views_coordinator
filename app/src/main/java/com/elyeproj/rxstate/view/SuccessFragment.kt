package com.elyeproj.rxstate.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.elyeproj.rxstate.R
import com.elyeproj.rxstate.coordinator.MainCoordinator
import com.elyeproj.rxstate.coordinator.Presentation
import com.elyeproj.rxstate.coordinator.Presenter
import com.elyeproj.rxstate.model.DataModel
import kotlinx.android.synthetic.main.view_success.*

class SuccessFragment : Fragment(), Presenter {
    companion object {
        const val SUCCESS_STATE = "SuccessState"
    }

    lateinit var dataModel: DataModel
    override lateinit var coordinator: MainCoordinator

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.view_success, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        savedInstanceState?.let {
            dataModel = it.getSerializable(SUCCESS_STATE) as DataModel
        }
        txt_data.text = dataModel.dataString
        container.setOnClickListener { coordinator.toast("Success Fragment") }
    }

    override fun setData(presentable: Presentation) {
        dataModel = (presentable as SuccessPresentation).successData
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(SUCCESS_STATE, dataModel)
    }
}
