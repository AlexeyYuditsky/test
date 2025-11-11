package com.alexeyyuditsky.test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository = Repository(),
) : ViewModel() {

    private val _flow = MutableStateFlow("")
    val flow = _flow.asStateFlow()

    init {
        getNewsSimultaneously()
    }

    private fun getNewsSimultaneously() = viewModelScope.launch {
        _flow.value = (0 until 3)
            .map {
                async(Dispatchers.IO) {
                    repository.getNews(it)
                }
            }
            .awaitAll()
            .joinToString()
    }
}

fun main() {
    val list = listOf(1,2,3)
    val res = list.map {
        println("testtest it")
        "$it la"
    }.toList()

    println(res)
}