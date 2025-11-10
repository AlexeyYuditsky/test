package com.alexeyyuditsky.test

import kotlinx.coroutines.delay

class Repository {

    suspend fun getNews(page: Int): List<String> {
        delay(1000)
        return listOf("$page")
    }

}
