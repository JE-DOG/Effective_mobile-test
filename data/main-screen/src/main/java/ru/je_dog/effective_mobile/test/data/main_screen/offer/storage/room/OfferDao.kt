package ru.je_dog.effective_mobile.test.data.main_screen.offer.storage.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.je_dog.effective_mobile.test.core.data.model.offer.entity.OfferEntity

@Dao
interface OfferDao {
    @Query("SELECT * FROM ${OfferEntity.TABLE_NAME}")
    suspend fun getOffers(): List<OfferEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOffers(offers: List<OfferEntity>)

}