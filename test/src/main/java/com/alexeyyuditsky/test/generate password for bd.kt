package com.alexeyyuditsky.test

import org.mindrot.jbcrypt.BCrypt

fun main() {
    println(BCrypt.hashpw("0000", BCrypt.gensalt(12)))
}
