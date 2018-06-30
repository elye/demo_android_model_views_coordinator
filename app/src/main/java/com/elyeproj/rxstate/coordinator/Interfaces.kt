package com.elyeproj.rxstate.coordinator

import android.support.v4.app.Fragment
import java.io.Serializable

interface MainView {
    fun showView(presentation: Presentation)
    fun toast(message: String)
}

abstract class Presentation {
    open val data: Serializable? = null
    abstract fun getViewClass(): Class<out Fragment>
}

interface ViewPresentation {
    var coordinator: MainCoordinator
}
