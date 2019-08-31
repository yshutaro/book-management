package book.management

interface BookRepository {

    fun create(name: String, author: String, publisher: String): Book

    fun update(book: Book)

    fun delete(id: Long)

    fun findAll(): List<Book>

    fun findById(id: Long): Book?

    fun search(name: String, author: String, publisher: String): List<Book>
}
