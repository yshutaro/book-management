package book.management

interface BookRepository {

    fun create(name: String, author: String, publisher: String): Book

    fun update(book: Book)

    fun delete(id: Long)

    fun findAll(): List<Book>

    fun findById(id: Long): Book?

    /*
    fun findByName(name: String): List<Book>

    fun findByAuthor(author: String): List<Book>

    fun findByPublisher(publisher: String): List<Book>

    fun findByNameAndAuthor(name: String, author: String): List<Book>

    fun findByNameAndPublisher(name: String, publisher: String): List<Book>
     */

    fun search(name: String, author: String, publisher: String): List<Book>
}