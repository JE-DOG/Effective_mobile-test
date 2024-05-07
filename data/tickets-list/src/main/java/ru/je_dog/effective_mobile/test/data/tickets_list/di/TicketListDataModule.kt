package ru.je_dog.effective_mobile.test.data.tickets_list.di

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.je_dog.effective_mobile.test.data.tickets_list.TicketDetailRepositoryImpl
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailApi
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailNetworkDataSource
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailNetworkDataSourceImpl
import ru.je_dog.effective_mobile.test.domain.tickets_list.TicketDetailRepository

@Module
class TicketListDataModule {

    @Provides
    fun provideTicketDetailApi(
        retrofit: Retrofit
    ): TicketDetailApi {
        return retrofit.create()
    }

    @Provides
    fun provideTicketListNetworkDataSource(
        ticketDetailApi: TicketDetailApi
    ): TicketDetailNetworkDataSource {
        return TicketDetailNetworkDataSourceImpl(
            ticketDetailApi
        )
    }

    @Provides
    fun provideTicketDetailRepository(
        ticketDetailNetworkDataSource: TicketDetailNetworkDataSource
    ): TicketDetailRepository {
        return TicketDetailRepositoryImpl(
            ticketDetailNetworkDataSource
        )
    }

}