package com.sch.sch_taxi.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sch.data.model.remote.error.InvalidAccessTokenException
import com.sch.data.model.remote.error.InvalidAccessTokenExpire
import com.sch.sch_taxi.di.PresentationApplication.Companion.editor
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    private val _errorEvent: MutableSharedFlow<Throwable> = MutableSharedFlow()
    val errorEvent: SharedFlow<Throwable> = _errorEvent.asSharedFlow()

    private val _loadingEvent: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val loadingEvent: SharedFlow<Boolean> = _loadingEvent.asSharedFlow()

    private val errorHandler = CoroutineExceptionHandler { CoroutineContext, throwable ->
        viewModelScope.launch(CoroutineContext) {
            _errorEvent.emit(throwable)
        }
    }

    protected val _toastMessage = MutableSharedFlow<String>()
    val toastMessage: SharedFlow<String>
        get() = _toastMessage

    private val _needLoginEvent: MutableSharedFlow<Boolean> = MutableSharedFlow<Boolean>()
    val needLoginEvent: SharedFlow<Boolean> = _needLoginEvent

    fun catchError(e: Throwable?) {
        viewModelScope.launch(errorHandler) {
            e?.let {
                when(it) {
                    is InvalidAccessTokenException -> {
                        editor.remove("accessToken")
                        editor.remove("refreshToken")
                        editor.commit()
                        _needLoginEvent.emit(true)
                    }
                    is InvalidAccessTokenExpire -> {
                        editor.remove("accessToken")
                        editor.remove("refreshToken")
                        editor.commit()
                        _needLoginEvent.emit(true)
                    }
                    else -> _errorEvent.emit(it)
                }
            }
            dismissLoading()
        }
    }

    fun showLoading() {
        baseViewModelScope.launch {
            _loadingEvent.emit(false)
        }
    }

    fun dismissLoading() {
        baseViewModelScope.launch {
            _loadingEvent.emit(true)
        }
    }

    protected val baseViewModelScope: CoroutineScope = viewModelScope + errorHandler
}