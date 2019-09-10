package book.management.controller

import book.management.Book
import book.management.BookForm
import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException
import org.assertj.core.api.Assertions.assertThat

object BooksControllerTest : Spek({


    describe("/books") {
        val embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)
        val client : HttpClient = HttpClient.create(embeddedServer.url)

        it ("top page") {
            val body = client.toBlocking().retrieve("/books")
            val books = listOf<Book>()
            val form = BookForm()
            val result =  HttpResponse.ok(mapOf("books" to books, "bookForm" to form))
            //TODO 正しくテスト出来ていない？
//            val result =  HttpResponse.ok("aa") // これでも通ってしまう。
            assertEquals(result, body)
        }

        it ("new page") {
            val body = client.toBlocking().retrieve("/books/new")
            val form = BookForm()
            val result:HttpResponse<*> =  HttpResponse.ok(mapOf("bookForm" to form))
            //TODO 正しくテスト出来ていない？
            assertEquals(result, body)
        }

        it ("edit page") {
            val body = client.toBlocking().retrieve("/books/[0-9].*/edit")
            //TODO 正しくテスト出来ていない？
            assertThat(body).contains("書籍変更")
        }

        it ("no page") {
            val body = client.toBlocking().retrieve("/hello")
//            val body = client?.toBlocking()?.retrieve("/books")
//            assertEquals("", body)
            assertNotNull(body)
        }

        afterGroup {
            client.close()
            embeddedServer.close()
        }
    }
})
