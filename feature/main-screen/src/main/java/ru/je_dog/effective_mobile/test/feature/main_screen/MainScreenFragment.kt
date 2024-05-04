package ru.je_dog.effective_mobile.test.feature.main_screen

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.je_dog.effective_mobile.test.core.domain.model.Offer
import ru.je_dog.effective_mobile.test.feature.main_screen.databinding.FragmentMainScreenBinding
import ru.je_dog.effective_mobile.test.feature.main_screen.databinding.ItemOfferBinding
import ru.je_dog.effective_mobile.test.feature.main_screen.di.DaggerMainScreenComponent
import ru.je_dog.effective_mobile.test.feature.main_screen.di.deps.MainScreenComponentDepsProvider
import ru.je_dog.effective_mobile.test.feature.main_screen.rcv.OfferAdapter
import ru.je_dog.effective_mobile.test.feature.main_screen.vm.MainScreenEffect
import ru.je_dog.effective_mobile.test.feature.main_screen.vm.MainScreenViewModel
import javax.inject.Inject

class MainScreenFragment: Fragment() {

    @Inject
    internal lateinit var viewModel: MainScreenViewModel

    private lateinit var binding: FragmentMainScreenBinding
    private val mainScreenComponent by lazy {
        val deps = MainScreenComponentDepsProvider.deps

        DaggerMainScreenComponent.factory()
            .create(deps)
    }
    private val offerAdapter = OfferAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        mainScreenComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainScreenBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() = with(binding) {
        with(viewModel){
            getOffers()
            getLastCityCityFromInput()
        }
        with(rcvOffer){
            adapter = offerAdapter.adapter
        }
        //Layout
        inputCityFrom.addTextChangedListener { text: Editable? ->
            text?.let {
                viewModel.setCityFrom(text.toString())
            }
        }
        inputCityTo.setOnClickListener {
            //TODO
        }
        //view model states
        collectState()
        collectEffect()
    }

    private fun collectState() {
        viewModel.state
            .onEach { state ->
                offerAdapter.setOffers(state.offers)
            }
            .launchIn(lifecycleScope)
    }

    private fun collectEffect() {
        viewModel.effect
            .onEach {  effect ->
                when(effect){
                    is MainScreenEffect.ShowToast -> {
                        Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                    }

                    is MainScreenEffect.SetCityFromText -> {
                        binding.inputCityFrom.setText(effect.cityName)
                    }

                    null -> {}
                }
            }
            .launchIn(lifecycleScope)
    }


}