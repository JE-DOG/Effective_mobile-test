package ru.je_dog.effective_mobile.test.data.tickets_list.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.je_dog.effective_mobile.test.data.tickets_list.TicketDetailRepositoryImpl
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailApi
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailNetworkDataSource
import ru.je_dog.effective_mobile.test.data.tickets_list.network.TicketDetailNetworkDataSourceImpl
import ru.je_dog.effective_mobile.test.data.tickets_list.storage.TicketDetailStorageDataSource
import ru.je_dog.effective_mobile.test.data.tickets_list.storage.TicketDetailStorageDataSourceImpl
import ru.je_dog.effective_mobile.test.data.tickets_list.storage.room.TicketDetailDao
import ru.je_dog.effective_mobile.test.data.tickets_list.storage.room.TicketDetailDatabase
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
            ticketDetailApi = ticketDetailApi
        )
    }

    @Provides
    fun provideTicketDetailStorageDataSource(
        ticketDetailDao: TicketDetailDao
    ): TicketDetailStorageDataSource {
        return TicketDetailStorageDataSourceImpl(
            ticketDetailDao = ticketDetailDao
        )
    }

    @Provides
    fun provideTicketDetailDatabase(
        context: Context
    ): TicketDetailDatabase {
        return Room.databaseBuilder(
            context = context,
            klass = TicketDetailDatabase::class.java,
            name = "ticket_detail_database"
        )
            .build()
    }

    @Provides
    fun provideTicketDetailDao(
        ticketDetailDatabase: TicketDetailDatabase
    ): TicketDetailDao {
        return ticketDetailDatabase.getTicketDetailDao()
    }

    @Provides
    fun provideTicketDetailRepository(
        ticketDetailNetworkDataSource: TicketDetailNetworkDataSource,
        storageDataSource: TicketDetailStorageDataSource,
    ): TicketDetailRepository {
        return TicketDetailRepositoryImpl(
            ticketDetailNetworkDataSource = ticketDetailNetworkDataSource,
            storageDataSource = storageDataSource
        )
    }

}