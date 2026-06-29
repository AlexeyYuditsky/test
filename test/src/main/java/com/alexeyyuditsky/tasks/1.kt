package com.alexeyyuditsky.tasks

import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

fun main(): Unit = runBlocking {
    val a = launch {
        while (true) {
            Thread.sleep(1000)
            println("Show Loading")
        }
    }

    launch {
        withContext(Dispatchers.IO) {
            delay(2500.milliseconds)
            a.cancel()
            println("Finished")
        }
    }
}