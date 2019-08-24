package book.management

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.HttpStatus
import io.micronaut.http.HttpResponse
import io.micronaut.views.*

@Controller("/books")
class BooksController(private val bookRepository: BookRepository) {

    @View("index")
    @Get("/")
    fun index(): HttpResponse<Map<String, List<Book>>> {
//        val books = listOf(
//                Book(1, "FACT FULLNESS", "ハンズ・ロスリング", "日経BP"),
//                Book(2, "三体", "劉 慈欣", "早川書房")
//        )
        val books = bookRepository.findAll()
        return HttpResponse.ok(mapOf("books" to books))
    }
}
