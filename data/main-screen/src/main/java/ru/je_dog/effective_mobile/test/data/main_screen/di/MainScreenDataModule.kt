package ru.je_dog.effective_mobile.test.data.main_screen.di

import android.content.Context
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.create
import ru.je_dog.effective_mobile.test.data.main_screen.cache.MainScreenCacheRepositoryImpl
import ru.je_dog.effective_mobile.test.data.main_screen.cache.storage.MainScreenCacheStorageDataSource
import ru.je_dog.effective_mobile.test.data.main_screen.cache.storage.MainScreenCacheStorageDataSourceImpl
import ru.je_dog.effective_mobile.test.data.main_screen.offer.OfferRepositoryImpl
import ru.je_dog.effective_mobile.test.data.main_screen.offer.network.OfferApi
import ru.je_dog.effective_mobile.test.data.main_screen.offer.network.OfferNetworkDataSource
import ru.je_dog.effective_mobile.test.data.main_screen.offer.network.OfferNetworkDataSourceImpl
import ru.je_dog.effective_mobile.test.domain.main_screen.MainScreenCacheRepository
import ru.je_dog.effective_mobile.test.domain.main_screen.OfferRepository

@Module
class MainScreenDataModule {

    @Provides
    fun provideOfferApi(
        retrofit: Retrofit
    ): OfferApi {
        return retrofit.create<OfferApi>()
    }

    @Provides
    fun provideOfferNetworkDataSource(
        offerApi: OfferApi
    ): OfferNetworkDataSource {
        return OfferNetworkDataSourceImpl(
            offerApi = offerApi
        )
    }

    @Provides
    fun provideOfferRepository(
        offerNetworkDataSource: OfferNetworkDataSource
    ): OfferRepository {
        return OfferRepositoryImpl(
            offerNetworkDataSource
        )
    }

    @Provides
    fun provideMainScreenCacheStorageDataSource(
        context: Context
    ): MainScreenCacheStorageDataSource {
        return MainScreenCacheStorageDataSourceImpl(
            context
        )
    }

    @Provides
    fun provideMainScreenCacheRepository(
        mainScreenCacheStorageDataSource: MainScreenCacheStorageDataSource
    ): MainScreenCacheRepository {
        return MainScreenCacheRepositoryImpl(
            mainScreenCacheStorageDataSource
        )
    }

}