package ru.je_dog.effective_mobile.test.feature.search_tickets.ui

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket
import ru.je_dog.effective_mobile.test.core.feature.R
import ru.je_dog.effective_mobile.test.feature.search_tickets.databinding.ItemTicketBinding
import java.lang.StringBuilder

class TicketAdapter {

    private val images = mapOf<Int,@receiver:DrawableRes Int>(
        0 to R.drawable.bg_circle_red,
        1 to R.drawable.bg_circle_blue,
        2 to R.drawable.bg_circle_white,
    )
    private val timeRangeCache = mutableMapOf<List<String>,String>()

    private val ticketDelegate = adapterDelegateViewBinding<Ticket, Ticket, ItemTicketBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemTicketBinding.inflate(layoutInflater,parent,false)
        }
    ) {
        bind {
            with(binding){
                images[layoutPosition]?.let { offerImageRes ->
                    image.setBackgroundResource(offerImageRes)
                }
                priceText.text = context.getString(R.string.search_tickets_screen_price, item.price.toString())
                titleText.text = item.title
                timeText.text = getTimeRange(item.timeRange)
            }
        }
    }

    val adapter = ListDelegationAdapter(ticketDelegate)

    fun setTickets(list: List<Ticket>) {
        val newTickets = list.take(3)
        val diffUtillCallback = TicketAdapterDiffUtill(
            newList = newTickets,
            oldList = adapter.items ?: emptyList()
        )
        val diffUtillResult = DiffUtil.calculateDiff(diffUtillCallback)

        adapter.items = newTickets
        diffUtillResult.dispatchUpdatesTo(adapter)
    }

    private fun getTimeRange(timeRange: List<String>): String {
        val cacheTimeRange = timeRangeCache[timeRange]
        return if (cacheTimeRange != null){
            cacheTimeRange
        }else {
            val stringTimeRange = StringBuilder()
            timeRange.forEachIndexed { index, time ->
                stringTimeRange.append("$time")
                if (index != timeRange.size - 1){
                    stringTimeRange.append("  ")
                }
            }
            timeRangeCache[timeRange] = stringTimeRange.toString()
            stringTimeRange.toString()
        }
    }
}