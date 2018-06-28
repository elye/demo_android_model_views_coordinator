package com.elyeproj.rxstate.coordinator

import android.support.v4.app.Fragment

interface MainView {
    fun showView(view: Presentation)
    fun toast(message: String)
}

interface Presentation {
    fun getViewClass() : Class<out Fragment>
}

interface Presenter {
    var coordinator: MainCoordinator
    fun setData(presentable: Presentation)
}
