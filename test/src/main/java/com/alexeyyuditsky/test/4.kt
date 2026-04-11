package com.alexeyyuditsky.test

fun getLongestWord(str: String) = str
    .split("[^\\p{L}]+".toRegex())
    .maxByOrNull { it.length }

fun main() {
    println(getLongestWord("Большой цветок"))
    println(getLongestWord("Я люблю печеньки"))
    println(getLongestWord("На улице сегодня солнечно, дождя нет"))
}