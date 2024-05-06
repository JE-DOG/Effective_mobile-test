package ru.je_dog.effective_mobile.test.data.search_tickets.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.TicketRepositoryImpl
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.network.TicketNetworkDataSource
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.network.TicketNetworkDataSourceImpl
import ru.je_dog.effective_mobile.test.data.search_tickets.offer.network.TicketApi
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
    fun provideTicketRepository(
        ticketNetworkDataSource: TicketNetworkDataSource
    ): TicketRepository {
        return TicketRepositoryImpl(
            ticketNetworkDataSource
        )
    }

}