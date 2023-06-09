package com.sch.sch_taxi.ui.chat

import com.sch.domain.model.Taxis
import com.sch.sch_taxi.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
) : BaseViewModel(), ChatActionHandler {

    private val TAG = "ChatViewModel"

    private val _navigationHandler: MutableSharedFlow<ChatNavigationAction> =
        MutableSharedFlow<ChatNavigationAction>()
    val navigationHandler: SharedFlow<ChatNavigationAction> =
        _navigationHandler.asSharedFlow()

    private val _notificationsEvent: MutableStateFlow<Taxis> =
        MutableStateFlow(Taxis(emptyList()))
    val notificationsEvent: StateFlow<Taxis> = _notificationsEvent

    override fun onClickedBack() {
        baseViewModelScope.launch {
            _navigationHandler.emit(ChatNavigationAction.NavigateToBack)
        }
    }

    override fun onClickedNotification() {
        baseViewModelScope.launch {
            _navigationHandler.emit(ChatNavigationAction.NavigateToBack)
        }
    }
}