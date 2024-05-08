package ru.je_dog.effective_mobile.test.core.feature.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {

    /**
     * False = offline, true = online
     * */
    fun broadcastNetworkState(): Flow<Boolean>
}