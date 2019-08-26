package book.management

interface BookRepository {

    fun create(name: String, author: String, publisher: String): Book

    fun update(book: Book)

    fun findAll(): List<Book>

    fun findById(id: Long): Book?
}