package com.alexeyyuditsky.test

import kotlinx.coroutines.*

fun main() {
    runBlocking(Dispatchers.Default) {
        launch {
            while (true) {
                println("first ${Thread.currentThread().name}")
            }
        }

        launch {
            println("second ${Thread.currentThread().name}")
        }
    }
}