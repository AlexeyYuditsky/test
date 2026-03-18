package com.alexeyyuditsky.test

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

suspend fun main() {
    coroutineScope {
        launch(context = CoroutineName("one") + Job() + Dispatchers.Default + SupervisorJob() + CoroutineExceptionHandler { context, throwable ->  }) {

        }
    }
}