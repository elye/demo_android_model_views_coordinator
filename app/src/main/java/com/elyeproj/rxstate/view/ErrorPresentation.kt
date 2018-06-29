package com.elyeproj.rxstate.view

import com.elyeproj.rxstate.coordinator.Presentation
import java.io.Serializable

class ErrorPresentation(errorMessage: String): Presentation() {
    data class State(val errorMessage: String) : Serializable
    override val state = State(errorMessage)
    override fun getViewClass() = ErrorFragment::class.java
}
