package ru.je_dog.effective_mobile.test.feature.search_tickets

import android.content.Intent
import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.je_dog.effective_mobile.test.core.feature.ext.setOnEndDrawableClickListener
import ru.je_dog.effective_mobile.test.core.feature.navigation.Coordinator
import ru.je_dog.effective_mobile.test.feature.search_tickets.databinding.FragmentSearchTicketsBinding
import ru.je_dog.effective_mobile.test.feature.search_tickets.di.DaggerSearchTicketsComponent
import ru.je_dog.effective_mobile.test.feature.search_tickets.di.deps.SearchTicketsComponentDepsProvider
import ru.je_dog.effective_mobile.test.feature.search_tickets.ui.TicketAdapter
import ru.je_dog.effective_mobile.test.feature.search_tickets.vm.SearchTicketsEffect
import ru.je_dog.effective_mobile.test.feature.search_tickets.vm.SearchTicketsViewModel
import javax.inject.Inject

class SearchTicketsFragment: Fragment() {

    @Inject
    lateinit var coordinator: Coordinator
    @Inject
    lateinit var viewModel: SearchTicketsViewModel
    private lateinit var binding: FragmentSearchTicketsBinding
    private val component by lazy {
        val deps = SearchTicketsComponentDepsProvider.deps

        DaggerSearchTicketsComponent.factory()
            .create(deps)
    }
    private val ticketAdapter = TicketAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchTicketsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    private fun initUi() = with(binding) {
        //ViewModel
        viewModel.getTickets()
        collectState()
        collectEffect()
        //Layout
        inputCityTo.apply {
            val cityTo = arguments?.getString(CITY_TO_KEY) ?: ""
            setText(cityTo)
        }
        inputCityFrom.apply {
            val cityFrom = arguments?.getString(CITY_FROM_KEY) ?: ""
            setText(cityFrom)

            setOnEndDrawableClickListener {
                val inputCityFromName = inputCityFrom.text.toString()
                inputCityFrom.setText(inputCityTo.text.toString())
                inputCityTo.setText(inputCityFromName)
            }
        }
        backReelsChip.setOnClickListener {
            val insertCalendarIntent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
            startActivity(insertCalendarIntent)
        }
        backBtn.setOnClickListener {
            coordinator.back()
        }
        rcvTickets.adapter = ticketAdapter.adapter
        showAllTicketsBtn.setOnClickListener {
            coordinator.navigateToTicketsList(
                cityFrom = inputCityFrom.text.toString(),
                cityTo = inputCityTo.text.toString()
            )
        }
    }

    private fun collectEffect() {
        viewModel.effect
            .onEach { effect ->
                when(effect) {

                    is SearchTicketsEffect.ShowToast -> {
                        Toast.makeText(requireContext(), effect.message, Toast.LENGTH_SHORT).show()
                    }

                    null -> {}

                }
            }
            .launchIn(lifecycleScope)
    }

    private fun collectState() {
        viewModel.state
            .onEach { state ->
                ticketAdapter.setTickets(state.tickets)
            }
            .launchIn(lifecycleScope)
    }

    companion object {

        fun create(
            cityFrom: String,
            cityTo: String,
        ): SearchTicketsFragment {
            val fragment = SearchTicketsFragment().apply {
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