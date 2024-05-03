package ru.je_dog.effective_mobile.test.feature.main_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.je_dog.effective_mobile.test.feature.main_screen.databinding.FragmentMainScreenBinding
import ru.je_dog.effective_mobile.test.feature.main_screen.di.DaggerMainScreenComponent
import ru.je_dog.effective_mobile.test.feature.main_screen.di.deps.MainScreenComponentDepsProvider
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

        collectState()
        collectEffect()
    }

    private fun collectState() = with(binding) {
        viewModel.state
            .onEach { state ->

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

                    null -> {}
                }
            }
            .launchIn(lifecycleScope)
    }


}