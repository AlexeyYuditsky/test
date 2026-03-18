package com.alexeyyuditsky.test

import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope

suspend fun main(): Unit = coroutineScope {
    val job: Job = Job()
    println(job.start())
}
