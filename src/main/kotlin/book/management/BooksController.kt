package book.management

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
//import io.micronaut.http.HttpStatus

@Controller("/books")
class BooksController {

    @Get("/")
    fun index(): String {
        val books = listOf(
                Book(1, "FACT FULLNESS", "ハンズ・ロスリング", "日経BP"),
                Book(2, "三体", "劉 慈欣", "早川書房")
        )
        return books.toString()
    }
}