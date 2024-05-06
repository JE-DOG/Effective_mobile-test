package ru.je_dog.effective_mobile.test.feature.search_tickets.vm

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
import ru.je_dog.effective_mobile.test.domain.search_tickets.TicketRepository
import ru.je_dog.effective_mobile.test.feature.search_tickets.di.SearchTicketsComponentScope
import javax.inject.Inject

@SearchTicketsComponentScope
class SearchTicketsViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
): ViewModel() {

    private val _state = MutableStateFlow(SearchTicketsViewState())
    val state: StateFlow<SearchTicketsViewState> = _state

    private val _effect: MutableStateFlow<SearchTicketsEffect?> = MutableStateFlow(null)
    val effect: StateFlow<SearchTicketsEffect?> = _effect

    fun getTickets() {
        ticketRepository.getTickets()
            .flowOn(Dispatchers.IO)
            .catch { throwable ->
                Log.e("ErrorTag",throwable.message ?: "")
                Log.e("ErrorTag",throwable.localizedMessage ?: "")
                val message = throwable.message ?: "Что-то пошло не так"
                _effect.update {
                    SearchTicketsEffect.ShowToast(message)
                }
            }
            .onEach { tickets ->
                _state.update { state ->
                    state.copy(
                        tickets = tickets
                    )
                }
            }
            .launchIn(viewModelScope)
    }

}