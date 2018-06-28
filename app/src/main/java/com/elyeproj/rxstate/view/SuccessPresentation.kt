package com.elyeproj.rxstate.view

import com.elyeproj.rxstate.coordinator.Presentation
import com.elyeproj.rxstate.model.DataModel

class SuccessPresentation(val successData: DataModel): Presentation {
    override fun getViewClass() = SuccessFragment::class.java
}
