package com.elyeproj.rxstate.view

import com.elyeproj.rxstate.coordinator.Presentation
import com.elyeproj.rxstate.model.DataModel
import java.io.Serializable

class SuccessPresentation(successData: DataModel): Presentation() {
    data class State(val successData: DataModel) : Serializable
    override val state = State(successData)
    override fun getViewClass() = SuccessFragment::class.java

}
