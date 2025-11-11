package com.alexeyyuditsky.test

import kotlinx.coroutines.delay

class Repository {

    suspend fun getNews(page: Int): String {
        delay(1000)
        return "page: $page"
    }

}
