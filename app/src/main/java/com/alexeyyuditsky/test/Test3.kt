package com.alexeyyuditsky.test

fun main() {
    val seq = sequence {
        yield(1)
        yield(2)
        yield(3)
    }

    for (num in seq) {
        print(num)
    }
}