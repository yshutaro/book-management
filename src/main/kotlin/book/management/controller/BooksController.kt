package book.management.controller

import book.management.Book
import book.management.BookForm
import book.management.repository.BookRepository
import io.micronaut.http.HttpResponse
import io.micronaut.views.View
import io.micronaut.http.MediaType
import io.micronaut.validation.Validated
import javax.validation.Valid
import io.micronaut.http.HttpStatus
import io.micronaut.http.annotation.*


@Validated
@Controller("/books")
class BooksController(private val bookRepository: BookRepository) {

    @View("not_found")
    @Error(status = HttpStatus.NOT_FOUND, global = true)
    fun notFound() {}

    @View("index")
    @Get("/")
    fun index(): HttpResponse<Map<String, Any>> {
        val books = listOf<Book>()
        val form = BookForm()
        return HttpResponse.ok(mapOf("books" to books, "bookForm" to form))
    }

    @View("index")
    @Post("/")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    fun searchbooks(@Body form: BookForm): HttpResponse<Map<String, Any>> {
        val books = bookRepository.search(form.name ?: "", form.author ?: "", form.publisher ?: "")
        return HttpResponse.ok(mapOf("books" to books, "bookForm" to form))
    }

    @View("new")
    @Get("new")
    // メソッド名がnewだとうまく動かない
    fun newbook(): HttpResponse<Map<String, BookForm>> {
        val form = BookForm()
        return HttpResponse.ok(mapOf("bookForm" to form))
    }

    @Post("/new")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    fun create(@Valid @Body form: BookForm): HttpResponse<Map<String, Book>> {
        val name = requireNotNull(form.name)
        val author = requireNotNull(form.author)
        val publisher = requireNotNull(form.publisher)
        val book = bookRepository.create(name, author, publisher)
        return HttpResponse.ok(mapOf("book" to book))
    }

    @View("edit")
    @Get("{id}/edit")
    fun edit(@PathVariable("id") id: Long): HttpResponse<Map<String, BookForm>> {
        val book = bookRepository.findById(id) ?: return HttpResponse.notFound()
        val form =BookForm()
        form.name = book.name
        form.author = book.author
        form.publisher = book.publisher
        return HttpResponse.ok(mapOf("bookForm" to form))
    }

    @Patch("{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    fun update(@PathVariable("id") id:Long, @Body @Valid form: BookForm): HttpResponse<Map<String, Book>> {
        val book = bookRepository.findById(id) ?: return HttpResponse.notFound()
        val newBook = book.copy(name = requireNotNull(form.name),
                                author = requireNotNull(form.author),
                                publisher = requireNotNull(form.publisher))
        bookRepository.update(newBook)
        return HttpResponse.ok(mapOf("book" to newBook))
    }

    @View("deleted")
    @Get("{id}/delete")
    fun delete(@PathVariable("id") id: Long): HttpResponse<Map<String, Book>> {
        val book = bookRepository.findById(id) ?: return HttpResponse.notFound()
        bookRepository.delete(book.id)
        return HttpResponse.ok(mapOf("book" to book))
    }

}
