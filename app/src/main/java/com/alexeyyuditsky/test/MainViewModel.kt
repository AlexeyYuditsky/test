package com.alexeyyuditsky.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.collections.emptyList

class MainViewModel(
    private val repository: Repository = Repository(),
) : ViewModel() {

    private val _flow = MutableStateFlow(emptyList<String>())
    val flow = _flow.asStateFlow()

    init {
        getNewsSimultaneously()
    }

    fun getNewsSimultaneously() = viewModelScope.launch {
        val news = (0 until 3)
            .map { page ->
                async {
                    println("testtest async start $page")
                    repository.getNews(page)
                }
            }.awaitAll()

        _flow.emit(news.flatten())
    }
}

fun main() {
    val list1 = listOf(1,4)
    val list2 = listOf(2)
    val list3 = listOf(3)
    val allList = listOf(list1, list2, list3)

    println(allList)
    println(allList.flatten())
}