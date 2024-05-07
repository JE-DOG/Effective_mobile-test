package ru.je_dog.effective_mobile.test.feature.tickets_list.ui

import androidx.recyclerview.widget.DiffUtil
import ru.je_dog.effective_mobile.test.core.domain.model.ticket_detail.TicketDetail

class TicketDetailAdapterDiffUtill(
    private val newList: List<TicketDetail>,
    private val oldList: List<TicketDetail>,
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newTicketDetail = newList[newItemPosition]
        val oldTicketDetail = newList[oldItemPosition]

        return newTicketDetail.id == oldTicketDetail.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newTicketDetail = newList[newItemPosition]
        val oldTicketDetail = newList[oldItemPosition]

        return newTicketDetail.departure.airport == oldTicketDetail.departure.airport &&
                newTicketDetail.price == oldTicketDetail.price &&
                newTicketDetail.hasTransfer == oldTicketDetail.hasTransfer &&
                newTicketDetail.badge == oldTicketDetail.badge &&
                newTicketDetail.departure.date == oldTicketDetail.departure.date &&
                newTicketDetail.arrival.date == oldTicketDetail.arrival.date &&
                newTicketDetail.arrival.airport == oldTicketDetail.arrival.airport

    }

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
}