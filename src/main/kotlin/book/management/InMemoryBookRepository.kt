package book.management

import javax.inject.Singleton

@Singleton
class InMemoryBookRepository : BookRepository {

    private val books: MutableList<Book> = mutableListOf()

    private val maxId: Long
        get() = books.map(Book::id).max() ?:0

    override fun create(name: String, author: String, publisher: String): Book {
        val id = maxId + 1
        val book = Book(id, name, author, publisher)
        books += book
        return book
    }

    override fun update(book: Book) {
        books.replaceAll { t ->
            if (t.id == book.id) book
            else t
        }
    }

    override fun findAll(): List<Book> = books.toList()

    override fun findById(id: Long): Book? = books.find { it.id == id}
}