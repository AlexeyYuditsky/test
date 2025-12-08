package com.alexeyyuditsky.test

import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

data class Location(
    val longitude: Double,
    val latitude: Double,
)

fun locationCallback(
    onSuccess: (Location) -> Unit,
    onFailed: (Throwable) -> Unit,
): () -> Unit {
    val thread = thread {
        try {
            Thread.sleep(1000)
            val location = Location(longitude = 25.34, latitude = 34.11)
            onSuccess(location)
        } catch (e: Throwable) {
            onFailed(e)
        }
    }

    return { thread.interrupt() }
}

suspend fun locationSuspend(): Location =
    suspendCancellableCoroutine { continuation ->
        val cancel = locationCallback(
            onSuccess = { location ->
                if (continuation.isActive) continuation.resume(location)
            },
            onFailed = { exception ->
                if (continuation.isActive) continuation.resumeWithException(exception)
            }
        )

        continuation.invokeOnCancellation {
            cancel()
        }
    }

suspend fun main() {
    try {
        val loc = locationSuspend()
        println(loc)
    } catch (e: Exception) {
        println(e)
    }
    delay(2000)
}
