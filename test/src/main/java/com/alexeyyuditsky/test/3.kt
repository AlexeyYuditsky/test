package com.alexeyyuditsky.test

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainCoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import sun.rmi.server.Dispatcher
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.EmptyCoroutineContext.fold

suspend fun main() {
    val ctx = CoroutineName("Name1") + Job()
    ctx.fold("") { acc, element ->
        println("acc: $acc")
        println("element: $element")
        "$acc$element "
    }.also(::println)/*
// CoroutineName(Name1) JobImpl{Active}@dbab622e
    val empty = emptyList<CoroutineContext>()
    ctx.fold(empty) { acc, element ->
        println("acc: $acc")
        println("element: $element")
        acc + element
    }
        .joinToString()
        .also(::println)
*/// CoroutineName(Name1), JobImpl{Active}@dbab622e
}