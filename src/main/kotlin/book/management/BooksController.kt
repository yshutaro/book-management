package book.management

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.HttpStatus
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.views.View
import io.micronaut.validation.Validated
import javax.validation.Valid

@Validated
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
    fun newbook(): HttpResponse<Map<String, BookForm>> {
        val form = BookForm()
        return HttpResponse.ok(mapOf("bookForm" to form))
    }

    @Post("/", consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    fun create(@Body @Valid form:BookForm): HttpResponse<String> {
        val name = requireNotNull(form.name)
        val author = requireNotNull(form.author)
        val publisher = requireNotNull(form.publisher)
        bookRepository.create(name, author, publisher)
        val location = java.net.URI("/books")
        return HttpResponse.redirect(location)
    }

    @View("edit")
    @Get("{id}/edit")
    fun edit(@PathVariable("id") id: Long): HttpResponse<Map<String, BookForm>> {
        val book = bookRepository.findById(id) ?: throw NotFoundException()
        val form = BookForm()
        form.name = book.name
        form.author = book.author
        form.publisher = book.publisher
        return HttpResponse.ok(mapOf("bookForm" to form))
    }

//    @Patch("{id}")
    @Post("{id}", consumes = [MediaType.APPLICATION_FORM_URLENCODED])
    fun update(@PathVariable("id") id:Long, @Body @Valid form:BookForm): HttpResponse<String> {
        val book = bookRepository.findById(id) ?: throw NotFoundException()

        val newBook = book.copy(name = requireNotNull(form.name),
                                author = requireNotNull(form.author),
                                publisher = requireNotNull(form.publisher))
        bookRepository.update(newBook)
        val location = java.net.URI("/books")
        return HttpResponse.redirect(location)
    }

    @Get("{id}/delete")
    fun delete(@PathVariable("id") id: Long): HttpResponse<String> {
        val book = bookRepository.findById(id) ?: throw NotFoundException()
        bookRepository.delete(book.id)
        val location = java.net.URI("/books")
        return HttpResponse.redirect(location)
    }

}
