package ru.je_dog.effective_mobile.test.feature.tickets_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.je_dog.effective_mobile.test.core.data.model.offer.ticket_details.FlightDto
import ru.je_dog.effective_mobile.test.core.data.model.offer.ticket_details.toDomain
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.feature.tickets_list.databinding.FragmentTicketsListBinding
import ru.je_dog.effective_mobile.test.feature.tickets_list.di.DaggerTicketsListComponent
import ru.je_dog.effective_mobile.test.feature.tickets_list.di.deps.TicketsListComponentDepsProvider
import ru.je_dog.effective_mobile.test.feature.tickets_list.ui.TicketDetailAdapter
import ru.je_dog.effective_mobile.test.feature.tickets_list.vm.TicketListEffect
import ru.je_dog.effective_mobile.test.feature.tickets_list.vm.TicketListViewModel
import javax.inject.Inject

class TicketsListFragment: Fragment() {

    @Inject
    lateinit var coordinator: Coordinator
    @Inject
    lateinit var viewModel: TicketListViewModel
    private lateinit var binding: FragmentTicketsListBinding
    private val component by lazy {
        val deps = TicketsListComponentDepsProvider.deps

        DaggerTicketsListComponent.factory()
            .create(deps)
    }
    private val ticketDetailAdapter = TicketDetailAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTicketsListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() = with(binding) {
        collectState()
        collectEffect()
        //Layout
        cityFromAndCityToText.apply {
            val cityFrom = arguments?.getString(CITY_FROM_KEY) ?: ""
            val cityTo = arguments?.getString(CITY_TO_KEY) ?: ""
            val cityFromAndCityTo = getString(ru.je_dog.effective_mobile.test.core.feature.R.string.tickets_list_screen_city_from_and_city_to,cityFrom,cityTo)
            text = cityFromAndCityTo
        }
        backBtn.setOnClickListener {
            coordinator.back()
        }
        rcvTickets.adapter = ticketDetailAdapter.adapter
    }

    private fun collectEffect() {
        viewModel.effect
            .onEach { efect ->
                when(efect){
                    is TicketListEffect.ShowToastText -> {
                        Toast.makeText(requireContext(), efect.message, Toast.LENGTH_SHORT).show()
                    }

                    null -> {}
                }
            }
    }

    private fun collectState() {
        viewModel.getTicketsDetail()
        viewModel.state
            .onEach { state ->
                ticketDetailAdapter.setTickets(state.ticketsDetail)
            }
            .launchIn(lifecycleScope)
    }

    companion object {
        fun create(
            cityFrom: String,
            cityTo: String,
        ): TicketsListFragment {
            val fragment = TicketsListFragment().apply {
                val bundle = Bundle().apply {
                    putString(CITY_FROM_KEY,cityFrom)
                    putString(CITY_TO_KEY,cityTo)
                }
                arguments = bundle
            }

            return fragment
        }

        private const val CITY_FROM_KEY = "CITY_FROM_KEY"
        private const val CITY_TO_KEY = "CITY_TO_KEY"
    }
}