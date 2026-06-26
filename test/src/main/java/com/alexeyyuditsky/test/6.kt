package com.alexeyyuditsky.test

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    runBlocking {
        launch {
            while (true) Thread.sleep(1000)
        }

        launch {
            delay(1000)
            println("1")
        }
    }
}