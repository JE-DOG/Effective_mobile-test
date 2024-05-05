package ru.je_dog.effective_mobile.test.feature.main_screen.ui.rcv.offer

import androidx.recyclerview.widget.DiffUtil
import ru.je_dog.effective_mobile.test.core.domain.model.Offer

class OfferAdapterDiffUtill(
    private val newList: List<Offer>,
    private val oldList: List<Offer>,
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
                newOffer.town == oldOffer.town &&
                newOffer.price == oldOffer.price
    }

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
}