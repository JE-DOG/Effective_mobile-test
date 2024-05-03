package ru.je_dog.effective_mobile.test.feature.main_screen.vm

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
import ru.je_dog.effective_mobile.test.domain.main_screen.MainScreenCacheRepository
import ru.je_dog.effective_mobile.test.domain.main_screen.OfferRepository
import ru.je_dog.effective_mobile.test.feature.main_screen.di.MainScreenComponentScope
import javax.inject.Inject

@MainScreenComponentScope
internal class MainScreenViewModel @Inject constructor(
    private val mainScreenCacheRepository: MainScreenCacheRepository,
    private val offerRepository: OfferRepository,
): ViewModel() {

    private val _state = MutableStateFlow(MainScreenViewState())
    val state: StateFlow<MainScreenViewState> = _state

    private val _effect: MutableStateFlow<MainScreenEffect?> = MutableStateFlow(null)
    val effect: StateFlow<MainScreenEffect?> = _effect


    fun getLastCityCityFromInput() {
        val lastCityFromInput = mainScreenCacheRepository.getLastUserCityInput()

        _state.update {
            it.copy(
                cityFrom = lastCityFromInput
            )
        }
    }

    fun getOffers() {
        offerRepository.getOffers()
            .flowOn(Dispatchers.IO)
            .onEach { offers ->
                _state.update {
                    it.copy(
                        offers = offers
                    )
                }
            }
            .catch { exception ->
                val message = exception.message ?: "Что-то пошло не так"
                _effect.update {
                    MainScreenEffect.ShowToast(message)
                }
            }
            .launchIn(viewModelScope)
    }

}