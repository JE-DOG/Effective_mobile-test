package ru.je_dog.effective_mobile.test.feature.main_screen.ui.rcv.offer

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.je_dog.effective_mobile.test.core.domain.model.Offer
import ru.je_dog.effective_mobile.test.core.feature.R
import ru.je_dog.effective_mobile.test.feature.main_screen.databinding.ItemOfferBinding

class OfferAdapter {

    private val images = mapOf<Int,@receiver:DrawableRes Int>(
        1 to R.drawable.img_first,
        2 to R.drawable.img_second,
        3 to R.drawable.img_third,
    )

    private val offerDelegate = adapterDelegateViewBinding<Offer, Offer, ItemOfferBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemOfferBinding.inflate(layoutInflater,parent,false)
        }
    ) {
        bind {
            with(binding){
                images[item.id]?.let { offerImageRes ->
                    image.setImageResource(offerImageRes)
                }
                price.text = context.getString(R.string.main_screen_offer_price, item.price.toString())
                town.text = item.town
                title.text = item.title
            }
        }
    }

    val adapter = ListDelegationAdapter(offerDelegate)

    fun setOffers(list: List<Offer>) {
        val diffUtillCallback = OfferAdapterDiffUtill(
            newList = list,
            oldList = adapter.items ?: emptyList()
        )
        val diffUtillResult = DiffUtil.calculateDiff(diffUtillCallback)

        adapter.items = list
        diffUtillResult.dispatchUpdatesTo(adapter)
    }

}