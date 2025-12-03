package com.alexeyyuditsky.test

import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.resume

data class User(
    val name: String = "Alex",
)

fun requestUser(func: (User) -> Unit) {
    thread {
        Thread.sleep(5000)
        func.invoke(User())
    }
}

suspend fun requestUser(): User {
    return suspendCancellableCoroutine { cont ->
        requestUser { user ->
            cont.resume(user)
        }
    }
}

suspend fun main() {
    println("Before")
    val user = requestUser()
    println(user)
}