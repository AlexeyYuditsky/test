package com.alexeyyuditsky.test

val seq = sequence {
    println("start")
    yield(1)
    println("end")
}

fun main() {
    val iterator = seq.iterator()
    println(iterator.next())
    println(iterator.next())
}