package ru.je_dog.effective_mobile.test.feature.search_tickets.ui

import androidx.recyclerview.widget.DiffUtil
import ru.je_dog.effective_mobile.test.core.domain.model.Ticket

class TicketAdapterDiffUtill(
    private val newList: List<Ticket>,
    private val oldList: List<Ticket>,
): DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newOffer = newList[newItemPosition]
        val oldOffer = newList[oldItemPosition]

        return newOffer.id == oldOffer.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newOffer = newList[newItemPosition]
        val oldOffer = newList[oldItemPosition]

        return newOffer.title == oldOffer.title &&
                newOffer.price == oldOffer.price
    }

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
}