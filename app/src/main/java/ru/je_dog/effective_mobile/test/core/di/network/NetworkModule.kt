package ru.je_dog.effective_mobile.test.core.di.network

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.je_dog.effective_mobile.test.di.AppComponentScope
import ru.je_dog.test.effective_mobile.BuildConfig

@Module
class NetworkModule {

    @Provides
    @AppComponentScope
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}