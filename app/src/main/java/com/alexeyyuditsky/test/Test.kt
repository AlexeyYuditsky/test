package com.alexeyyuditsky.test

import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class Language
class ArticleJson
class Article
class User
class UserRepo {
    suspend fun findUserByUUID(userUuid: String?): User {
        delay(1000)
        return User()
    }
}

class ArticleRepo {
    suspend fun getArticles(lang: Language): List<Article> {
        delay(2000)
        return listOf(Article(), Article(), Article(), Article(), Article())
    }
}

val formattedTime get() = LocalTime.now().format(DateTimeFormatter.ofPattern("ss:SS"))

class UseCase(
    val userRepo: UserRepo,
    val articleRepo: ArticleRepo,
) {
    suspend fun getAllArticles(
        userUuid: String?,
        lang: Language,
    ): List<ArticleJson> = coroutineScope {
       // println("start coroutineScope $formattedTime")
        val userDeferred = async {
            //println("start user async $formattedTime")
            userRepo.findUserByUUID(userUuid)
        }
        val articlesDeferred = async {
            //println("start articles async $formattedTime")
            articleRepo.getArticles(lang)
        }

        println("all await start $formattedTime")
        val user = userDeferred.await()
        println("user await end $formattedTime")
        val articles = articlesDeferred.await()
        println("articles await end $formattedTime")

        articles
            .filter { hasAccess(user, it) }
            .map { article -> async { toArticleJson(article) } }
            .awaitAll()
    }

    private suspend fun toArticleJson(article: Article): ArticleJson {
        delay(1000)
        return ArticleJson()
    }

    private fun hasAccess(user: User, article: Article): Boolean {
        return true
    }
}


suspend fun main() {
    val useCase = UseCase(UserRepo(), ArticleRepo())
    val result = useCase.getAllArticles("1", Language())
    println(result)
}