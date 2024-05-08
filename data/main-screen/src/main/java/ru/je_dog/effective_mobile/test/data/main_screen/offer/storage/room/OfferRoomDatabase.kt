package ru.je_dog.effective_mobile.test.data.main_screen.offer.storage.room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.je_dog.effective_mobile.test.core.data.model.offer.entity.OfferEntity


@Database(
    entities = [OfferEntity::class],
    version = 1
)
abstract class OfferRoomDatabase: RoomDatabase() {
    abstract fun getOfferDao(): OfferDao
}