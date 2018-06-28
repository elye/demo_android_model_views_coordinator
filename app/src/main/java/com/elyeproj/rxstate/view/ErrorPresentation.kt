package com.elyeproj.rxstate.view

import com.elyeproj.rxstate.coordinator.Presentation

class ErrorPresentation(val errorMessage: String): Presentation {
    override fun getViewClass() = ErrorFragment::class.java
}
