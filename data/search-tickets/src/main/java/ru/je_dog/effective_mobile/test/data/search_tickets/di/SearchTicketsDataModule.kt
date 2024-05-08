package ru.je_dog.effective_mobile.test.data.search_tickets.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.TicketRepositoryImpl
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.network.TicketNetworkDataSource
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.network.TicketNetworkDataSourceImpl
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.network.TicketApi
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.TicketStorageDataSource
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.TicketStorageDataSourceImpl
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.room.TicketDao
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.storage.room.TicketDatabase
import ru.je_dog.effective_mobile.test.domain.search_tickets.TicketRepository

@Module
class SearchTicketsDataModule {

    @Provides
    fun provideTicketApi(
        retrofit: Retrofit
    ): TicketApi {
        return retrofit.create<TicketApi>()
    }

    @Provides
    fun provideTicketNetworkDataSource(
        ticketApi: TicketApi
    ): TicketNetworkDataSource {
        return TicketNetworkDataSourceImpl(
            ticketApi = ticketApi
        )
    }

    @Provides
    fun provideTicketStorageDataSource(
        ticketDao: TicketDao
    ): TicketStorageDataSource {
        return TicketStorageDataSourceImpl(
            ticketDao = ticketDao
        )
    }

    @Provides
    fun provideTicketDao(
        ticketDataBase: TicketDatabase
    ): TicketDao {
        return ticketDataBase.getTicketDao()
    }

    @Provides
    fun provideTicketDatabase(
        context: Context
    ): TicketDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TicketDatabase::class.java,
            name = "ticket_database"
        )
            .build()
    }

    @Provides
    fun provideTicketRepository(
        ticketNetworkDataSource: TicketNetworkDataSource,
        storageDataSource: TicketStorageDataSource,
    ): TicketRepository {
        return TicketRepositoryImpl(
            networkDataSource = ticketNetworkDataSource,
            storageDataSource = storageDataSource
        )
    }

}