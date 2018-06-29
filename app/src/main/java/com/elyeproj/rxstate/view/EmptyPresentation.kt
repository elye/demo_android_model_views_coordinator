package com.elyeproj.rxstate.view

import com.elyeproj.rxstate.coordinator.Presentation

class EmptyPresentation: Presentation() {
    override fun getViewClass() = EmptyFragment::class.java
}
