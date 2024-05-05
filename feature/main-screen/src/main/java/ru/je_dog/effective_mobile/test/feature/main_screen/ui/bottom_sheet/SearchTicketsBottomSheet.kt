package ru.je_dog.effective_mobile.test.feature.main_screen.ui.bottom_sheet

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.InputFilter
import android.text.Spanned
import android.view.LayoutInflater
import android.view.MotionEvent.ACTION_UP
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.je_dog.effective_mobile.test.core.feature.ext.setInputFilterByRegex
import ru.je_dog.effective_mobile.test.core.feature.ext.setOnEndDrawableClickListener
import ru.je_dog.effective_mobile.test.feature.main_screen.databinding.FragmentSearchAviaTicketBinding
import ru.je_dog.effective_mobile.test.feature.main_screen.ui.rcv.cities.CityAdapter

internal class SearchTicketsBottomSheet: BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchAviaTicketBinding
    private lateinit var cityAdapter: CityAdapter
    private val coordinator by lazy {
        if (requireParentFragment() is SearchTicketsBottomSheetDeps){
            (requireParentFragment() as SearchTicketsBottomSheetDeps).coordinator
        }else {
            null
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchAviaTicketBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initUi()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initUi() = with(binding) {
        inputCityFrom.apply {
            val cityFrom = arguments?.getString(CITY_FROM_KEY) ?: ""

            setText(cityFrom)
        }
        inputCityTo.apply {
            setInputFilterByRegex()
            setOnEndDrawableClickListener {
                inputCityTo.setText("")
            }
        }
        rcvCities.apply {
            cityAdapter = CityAdapter(
                onItemClick = { city ->
                    val cityTo = getString(city.name)
                    val cityFrom = inputCityFrom.text.toString()
                    inputCityTo.setText(cityTo)


                    coordinator?.navigateToSearchTickets(
                        cityFrom = cityFrom,
                        cityTo = cityTo
                    )
                }
            )
            adapter = cityAdapter.adapter
        }
        shortCutsInit()
        root.minimumHeight = resources.displayMetrics.heightPixels
    }

    private fun shortCutsInit() = with(binding) {
        btnShortcutDifficultWay.setOnClickListener {
            val screenName = getString(ru.je_dog.effective_mobile.test.core.feature.R.string.search_avia_ticket_screen_shortcut_difficult_direction)
            coordinator?.navigateToPlaceholder(screenName)
        }
        btnShortcutWeekends.setOnClickListener {
            val screenName = getString(ru.je_dog.effective_mobile.test.core.feature.R.string.search_avia_ticket_screen_shortcut_weekends)
            coordinator?.navigateToPlaceholder(screenName)
        }
        btnShortcutHotTickets.setOnClickListener {
            val screenName = getString(ru.je_dog.effective_mobile.test.core.feature.R.string.search_avia_ticket_screen_shortcut_hot_tickets)
            coordinator?.navigateToPlaceholder(screenName)
        }
        btnShortcutSomewhere.setOnClickListener {
            val someCity = getString(ru.je_dog.effective_mobile.test.core.feature.R.string.search_avia_ticket_screen_shortcut_somewhere)
            inputCityTo.setText(someCity)
        }
    }

    companion object {
        fun create(cityFrom: String): SearchTicketsBottomSheet {
            val fragment = SearchTicketsBottomSheet().apply {
                val bundle = Bundle().apply {
                    putString(CITY_FROM_KEY,cityFrom)
                }
                arguments = bundle
            }

            return fragment
        }

        const val CITY_FROM_KEY = "CITY_FROM_KEY"
    }
}