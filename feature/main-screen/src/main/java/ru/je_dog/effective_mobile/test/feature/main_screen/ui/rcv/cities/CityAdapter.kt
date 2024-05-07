package ru.je_dog.effective_mobile.test.feature.main_screen.ui.rcv.cities

import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.je_dog.effective_mobile.test.core.feature.R
import ru.je_dog.effective_mobile.test.feature.main_screen.databinding.ItemCityBinding
import ru.je_dog.effective_mobile.test.feature.main_screen.ui.model.City

class CityAdapter(
    onItemClick: (City) -> Unit = {}
) {

    private val cityDelegate = adapterDelegateViewBinding<City, City, ItemCityBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemCityBinding.inflate(layoutInflater,parent,false)
        }
    ) {
        bind {
            with(binding){
                textCityName.setText(item.name)
                image.setImageResource(item.image)

                root.setOnClickListener {
                    onItemClick(item)
                }
            }
        }
    }

    val adapter = ListDelegationAdapter(cityDelegate).apply {
        items = MOCK_CITIES
    }

    companion object {
        private val MOCK_CITIES = listOf(
            City(
                R.drawable.img_stambul,
                R.string.stambul
            ),
            City(
                R.drawable.img_sochi,
                R.string.sochi
            ),
            City(
                R.drawable.img_phuket,
                R.string.phuket
            ),
        )
    }
}