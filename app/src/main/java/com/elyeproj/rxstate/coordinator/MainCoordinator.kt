package com.elyeproj.rxstate.coordinator

import com.elyeproj.rxstate.coordinator.DataSource.FetchStyle.*
import com.elyeproj.rxstate.model.UiStateModel
import com.elyeproj.rxstate.view.EmptyPresentation
import com.elyeproj.rxstate.view.ErrorPresentation
import com.elyeproj.rxstate.view.LoadingPresentation
import com.elyeproj.rxstate.view.SuccessPresentation
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.io.Serializable

class MainCoordinator(val view: MainView, val dataSource: DataSource) {
    private var disposable: Disposable? = null

    var isLoading = false

    fun initialize() {
        view.showView(EmptyPresentation())
    }

    fun loadSuccess() {
        dataSource.fetchStyle = FETCH_SUCCESS
        loadData()
    }

    fun loadError() {
        dataSource.fetchStyle = FETCH_ERROR
        loadData()
    }

    fun loadEmpty() {
        dataSource.fetchStyle = FETCH_EMPTY
        loadData()
    }

    fun loadData() {
        disposable?.dispose()

        disposable = dataSource.fetchData()
                .map { result -> UiStateModel.success(result) }
                .onErrorReturn { exception -> UiStateModel.error(exception) }
                .startWith(UiStateModel.loading())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ uiState ->
                    isLoading = uiState.isLoading()
                    view.showView(when {
                        uiState.isLoading() -> LoadingPresentation()
                        uiState.isError() -> ErrorPresentation(uiState.getErrorMessage())
                        uiState.isSuccess() -> SuccessPresentation(uiState.getData())
                        uiState.isEmpty() -> EmptyPresentation()
                        else -> throw IllegalArgumentException("Invalid Response")
                    })
                })
    }

    fun toast(message: String) {
        view.toast(message)
    }

    fun destroy() {
        disposable?.dispose()
    }

    fun getState() = State(isLoading, dataSource.fetchStyle)

    fun restoreState(state: Serializable) {
        if (state is State) {
            isLoading = state.isLoading
            if (isLoading) {
                when (state.fetchStyle) {
                    FETCH_EMPTY -> loadEmpty()
                    FETCH_ERROR -> loadError()
                    FETCH_SUCCESS -> loadSuccess()
                }
            }
        }
    }

    class State(val isLoading: Boolean, val fetchStyle: DataSource.FetchStyle): Serializable
}