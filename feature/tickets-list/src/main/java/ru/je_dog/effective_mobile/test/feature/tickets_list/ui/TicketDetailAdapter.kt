package ru.je_dog.effective_mobile.test.feature.tickets_list.ui

import android.content.Context
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail
import ru.je_dog.effective_mobile.test.core.feature.R
import ru.je_dog.effective_mobile.test.core.feature.ext.getHourDiff
import ru.je_dog.effective_mobile.test.core.feature.ext.simpleHourAndMinutes
import ru.je_dog.effective_mobile.test.feature.tickets_list.databinding.ItemTicketDetailBinding

class TicketDetailAdapter {

    private val ticketDetailDelegate = adapterDelegateViewBinding<TicketDetail, TicketDetail, ItemTicketDetailBinding>(
        viewBinding = { layoutInflater, parent ->
            ItemTicketDetailBinding.inflate(layoutInflater,parent,false)
        }
    ) {
        bind {
            with(binding){
                priceText.text = context.getString(R.string.search_tickets_screen_price, item.price.toString())
                arrivalAirportText.text = item.arrival.airport
                departureAirportText.text = item.departure.airport
                arrivalTimeText.text = item.arrival.date.simpleHourAndMinutes
                departureTimeText.text = item.departure.date.simpleHourAndMinutes
                if (item.badge != null){
                    badgeText.visibility = View.VISIBLE
                    badgeText.text = item.badge
                }
                inTheWayText.text = item.getInTheWayAndTransferText(context)
            }
        }
    }

    val adapter = ListDelegationAdapter(ticketDetailDelegate)

    fun setTickets(list: List<TicketDetail>) {
        val diffUtillCallback = TicketDetailAdapterDiffUtill(
            newList = list,
            oldList = adapter.items ?: emptyList()
        )
        val diffUtillResult = DiffUtil.calculateDiff(diffUtillCallback)

        adapter.items = list
        diffUtillResult.dispatchUpdatesTo(adapter)
    }

    private fun TicketDetail.getInTheWayAndTransferText(
        context: Context
    ): String {
        return StringBuilder().apply {
            val diffHour = StringBuilder().apply {
                val diffHours = arrival.date.getHourDiff(departure.date)
                if (diffHours % 1 == 0.0f){
                    append(diffHours.toInt())
                }else {
                    append(diffHours)
                }
            }
            val inTheWayText = context.getString(R.string.tickets_list_screen_in_the_way,diffHour.toString())
            append(inTheWayText)
            if (!hasTransfer){
                val withoutTransferText = context.getString(R.string.tickets_list_screen_without_transfer)
                append(" $withoutTransferText")
            }
        }.toString()
    }
}