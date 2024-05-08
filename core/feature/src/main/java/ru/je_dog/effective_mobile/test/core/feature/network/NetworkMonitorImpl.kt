package ru.je_dog.effective_mobile.test.core.feature.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class NetworkMonitorImpl(
    private val context: Context
): NetworkMonitor {

    private val connectivityManager by lazy {
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
    @SuppressLint("MissingPermission")
    override fun broadcastNetworkState(): Flow<Boolean> = callbackFlow {
        val currentState = connectivityManager.activeNetworkInfo?.isConnected ?: false
        send(currentState)
        val callback = object: ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                trySend(true)
            }

            override fun onLost(network: Network) {
                trySend(false)
            }

        }
        connectivityManager.registerDefaultNetworkCallback(callback)

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
}