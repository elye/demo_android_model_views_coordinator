package com.elyeproj.rxstate.coordinator

import android.support.v4.app.Fragment
import java.io.Serializable

interface MainView {
    fun showView(view: Presentation)
    fun toast(message: String)
}

abstract class Presentation {
    open val state: Serializable? = null
    abstract fun getViewClass(): Class<out Fragment>
    fun getData(): Serializable? = state
}

interface Presenter {
    var coordinator: MainCoordinator
}
