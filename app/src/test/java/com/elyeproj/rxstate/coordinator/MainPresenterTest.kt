package com.elyeproj.rxstate.coordinator


import com.elyeproj.rxstate.model.DataModel
import com.elyeproj.rxstate.view.EmptyPresentation
import com.elyeproj.rxstate.view.ErrorPresentation
import com.elyeproj.rxstate.view.LoadingPresentation
import com.elyeproj.rxstate.view.SuccessPresentation
import com.nhaarman.mockitokotlin2.argumentCaptor
import io.reactivex.Observable
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.`when` as whenever

class MainPresenterTest {

    @Mock
    lateinit var dataSource: DataSource

    @Mock
    lateinit var view: Container

    lateinit var presenter: Coordinator

    companion object {
        @ClassRule @JvmField
        val schedulers = RxImmediateSchedulerRule()
    }

    fun <T> any(): T {
        Mockito.any<T>()
        return null as T
    }

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = Coordinator(view, dataSource)
    }

    @Test
    fun wheneverFetchSuccessWithDataShouldResultSuccessWithData() {
        // Given
        val data = DataModel("Data Loaded")
        val observable = Observable.just(data)

        // When
        whenever(dataSource.fetchData()).thenReturn(observable)
        presenter.loadData()

        // Then
        val presentationCaptor = argumentCaptor<Presentation>()

        verify(view, times(2)).showView(presentationCaptor.capture())
        val presentationsCaptured = presentationCaptor.allValues
        Assert.assertTrue(presentationsCaptured[0] is LoadingPresentation)
        Assert.assertTrue(presentationsCaptured[1] is SuccessPresentation)
        assertEquals(data, (presentationsCaptured[1] as SuccessPresentation).successData)
    }

    @Test
    fun wheneverFetchSuccessWithoutDataShouldResultSuccessWithoutData() {
        // Given
        val data = DataModel("")
        val observable = Observable.just(data)

        // When
        whenever(dataSource.fetchData()).thenReturn(observable)
        presenter.loadData()

        // Then
        val presentationCaptor = argumentCaptor<Presentation>()
        verify(view, times(2)).showView(presentationCaptor.capture())
        val presentationsCaptured = presentationCaptor.allValues
        Assert.assertTrue(presentationsCaptured[0] is LoadingPresentation)
        Assert.assertTrue(presentationsCaptured[1] is EmptyPresentation)
    }

    @Test
    fun wheneverFetchErrorShouldResultError() {
        // Given
        val observable : Observable<DataModel> = Observable.create({
            subscriber -> subscriber.onError(IllegalArgumentException("Invalid Response"))
        })

        // When
        whenever(dataSource.fetchData()).thenReturn(observable)
        presenter.loadData()


        // Then
        val presentationCaptor = argumentCaptor<Presentation>()

        verify(view, times(2)).showView(presentationCaptor.capture())
        val presentationsCaptured = presentationCaptor.allValues
        Assert.assertTrue(presentationsCaptured[0] is LoadingPresentation)
        Assert.assertTrue(presentationsCaptured[1] is ErrorPresentation)
        assertEquals(IllegalArgumentException("Invalid Response").message,
                (presentationsCaptured[1] as ErrorPresentation).errorMessage)
    }
}