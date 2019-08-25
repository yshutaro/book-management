package book.management

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Body
import io.micronaut.http.HttpStatus
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.views.View

@Controller("/books")
class BooksController(private val bookRepository: BookRepository) {

    @View("index")
    @Get("/")
    fun index(): HttpResponse<Map<String, List<Book>>> {
        val books = bookRepository.findAll()
        return HttpResponse.ok(mapOf("books" to books))
    }

    @View("new")
    @Get("new")
    // メソッド名がnewだとうまく動かない
    fun newbook(): HttpResponse<Map<String, BookCreateForm>> {
        val form = BookCreateForm()
        return HttpResponse.ok(mapOf("bookCreateForm" to form))
    }

    @Post("/", consumes = [MediaType.APPLICATION_FORM_URLENCODED])
//    fun create(@Body form:BookCreateForm): HttpResponse<BookCreateForm> {
    fun create(name: String, author: String, publisher: String): HttpResponse<Map<String, BookCreateForm>> {
//        val name = requireNotNull(form.name)
//        val author = requireNotNull(form.author)
//        val publisher = requireNotNull(form.publisher)
        bookRepository.create(name, author, publisher)
//        return HttpResponse.created(form);
        val form = BookCreateForm()
        return HttpResponse.ok(mapOf("bookCreateForm" to form))
    }

}
