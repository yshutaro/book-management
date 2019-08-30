package book.management

import io.micronaut.context.annotation.Requires
import javax.inject.Singleton
import org.springframework.jdbc.core.JdbcTemplate
import book.management.service.TestService
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import javax.inject.Inject

//class JdbcBookRepository(private val jdbcTemplate: JdbcTemplate) : BookRepository {
//@Repository
@Singleton
class JdbcBookRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) : BookRepository {

    private val rowMapper = RowMapper<Book> { rs, _ ->
        Book(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getString("author"),
                rs.getString("publisher")
                )
    }

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

    override fun delete(id: Long) {
        books.toList().forEach {
            if (it.id == id) books.remove(it)
        }
    }

    override fun findAll(): List<Book> =
            jdbcTemplate.query("SELECT id, name, author, publisher FROM books", rowMapper)
//    override fun findAll(): List<Book> = books.toList()

    override fun findById(id: Long): Book? = books.find { it.id == id }

}