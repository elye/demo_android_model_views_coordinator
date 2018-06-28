package com.elyeproj.rxstate.view

import com.elyeproj.rxstate.coordinator.Presentation

class LoadingPresentation: Presentation {
    override fun getViewClass() = LoadingFragment::class.java
}
