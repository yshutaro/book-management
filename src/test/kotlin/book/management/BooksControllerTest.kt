package book.management

import io.micronaut.context.ApplicationContext
import io.micronaut.http.HttpStatus
import io.micronaut.runtime.server.EmbeddedServer
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.exceptions.HttpClientResponseException

class BooksControllerTest : Spek({

    var server: EmbeddedServer? = null
    var client: HttpClient? = null

    describe("/books") {
        beforeEachTest {
            server = ApplicationContext.run(EmbeddedServer::class.java)
            client = server?.applicationContext
                    ?.createBean(HttpClient::class.java, server?.url)
        }

        afterEachTest {
            server?.stop()
            client?.stop()
        }

        it ("top page") {
            val body = client?.toBlocking()?.retrieve("/books")
            assertNotNull(body)
//            assertEquals("", body)
        }

        it ("new page") {
            val body = client?.toBlocking()?.retrieve("/books/new")
            assertNotNull(body)
        }

        it ("no page") {
            try {
                client?.toBlocking()?.retrieve("/hello")
            }
            catch (e: HttpClientResponseException) {
                assertEquals(HttpStatus.NOT_FOUND, e.status)
                assertEquals("Page Not Found", e.message)
            }
        }
    }
})
