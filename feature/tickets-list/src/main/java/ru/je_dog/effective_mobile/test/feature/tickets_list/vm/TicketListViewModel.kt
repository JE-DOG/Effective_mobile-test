package ru.je_dog.effective_mobile.test.feature.tickets_list.vm

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import ru.je_dog.effective_mobile.test.domain.tickets_list.TicketDetailRepository
import ru.je_dog.effective_mobile.test.feature.tickets_list.di.TicketListComponentScope
import javax.inject.Inject

@TicketListComponentScope
class TicketListViewModel @Inject constructor(
    private val ticketDetailRepository: TicketDetailRepository
): ViewModel() {

    private val _state = MutableStateFlow(TicketListViewState())
    val state: StateFlow<TicketListViewState> = _state

    private val _effect: MutableStateFlow<TicketListEffect?> = MutableStateFlow(null)
    val effect: StateFlow<TicketListEffect?> = _effect

    fun getTicketsDetail() {
        ticketDetailRepository.getTicketsDetail()
            .flowOn(Dispatchers.IO)
            .catch { cause: Throwable ->
                val effect = TicketListEffect.ShowToastText(cause.message ?: "")
                Log.e("ErrorTag",cause.stackTraceToString())
                _effect.update {
                    effect
                }
            }
            .onEach { ticketDetails ->
                _state.update { state ->
                    state.copy(
                        ticketsDetail = ticketDetails
                    )
                }
            }
            .launchIn(viewModelScope)
    }

}